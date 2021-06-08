package montblanc.service;

import montblanc.Entity.EnrollmentStatus;
import montblanc.Entity.Role;
import montblanc.Entity.Token;
import montblanc.Entity.User;
import montblanc.dto.UserDTO;
import montblanc.exception.RoleNotFoundException;
import montblanc.exception.UserNotFoundException;
import montblanc.repository.EnrollmentStatusRepository;
import montblanc.repository.UserRepository;
import montblanc.util.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class AuthService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    EnrollmentStatusRepository esRepo;
    @Autowired
	private RoleService roleService;
    
    @Autowired
	private TokenService tokenService;

	@Autowired
	Email email;

	@Autowired
	EmailService emailService;

    @Transactional(readOnly = true)
    public UserDTO login(String email, String password) throws UserNotFoundException {
        System.out.println("username : " + email);
        System.out.println("password : " + password);
        User user = userRepo.findByEmailAndEnabled(email, true);
        System.out.println(user);

        if (null == user) throw new UserNotFoundException();
		
        boolean passwordCorrect = BCrypt.checkpw(password, user.getPassword());
        System.out.println(user);
        if (!passwordCorrect) throw new UserNotFoundException();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstName());
        userDTO.setLastname(user.getLastName());
        userDTO.setEmail(user.getEmail());
        List<String> rolesList = user.getRoles().stream().map(r -> {
            return r.getName();
        }).collect(Collectors.toList());
        userDTO.setRoles(rolesList);
        return userDTO;
    }
    @Transactional
	public void create(User user, HttpServletRequest request, ModelAndView modelAndView) throws RoleNotFoundException, MessagingException {
		Set<Role> roles = new HashSet<>();
		Role roleUser = roleService.findByName("ROLE_STUDENT");
		roles.add(roleUser);
		user.setRoles(roles);

		EnrollmentStatus es = esRepo.findByName("PENDING");
		user.setEnrollmentStatus(es);
		userRepo.save(user);

		Token token = new Token(user);
		tokenService.createToken(token);

		email.setTo(user.getEmail());
		email.setSubject("MBE - confirm your email");
		email.setTemplate("confirmAccount");

		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() +request.getContextPath()+"/";

		Map<String, Object> model = new HashMap<>();
		model.put("userIdentity", user.getLastName() + " " + user.getFirstName());
		model.put("appUrl", appUrl);
		model.put("urlToken", "confirm-account?token=");
		model.put("token", token.getToken());

		email.setModel(model);
		emailService.sendEmail(email);
	}
	public void confirmUserAccount(String confirmationToken) throws Exception {

		Token token = tokenService.findByToken(confirmationToken);

		if (token != null) {
			User user = userRepo.findByEmail(token.getUser().getEmail());
			if (user.getActivationDate() == null) {
				user.setEnabled(true);
				user.setActivationDate(LocalDate.now());
				userRepo.save(user);
			}
		} else {
			System.out.println("confirmUserAccount exception");
			throw new Exception("Invalid Token!");
		}
		
	}
}
