package montblanc.services;

import montblanc.exceptions.UserAlreadyExistException;
import montblanc.exceptions.UserNotFoundException;
import montblanc.persistence.dto.UserDto;
import montblanc.persistence.entities.User;
import montblanc.persistence.entities.VerificationToken;
import montblanc.persistence.repositories.RoleRepository;
import montblanc.persistence.repositories.UserRepository;
import montblanc.persistence.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static montblanc.security.SecurityConstants.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional(readOnly = true)
    public UserDto login(String email, String password) throws UserNotFoundException {
        System.out.println("username : " + email);
        System.out.println("password : " + password);
        User user = userRepository.findByEmail(email);
        System.out.println(user);

        if (null == user) throw new UserNotFoundException();

        boolean passwordCorrect = BCrypt.checkpw(password, user.getPassword());
        System.out.println(user);
        if (!passwordCorrect) throw new UserNotFoundException();

        UserDto userDTO = new UserDto();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        List<String> roles = user.getRoles().stream().map(r -> {
            return r.getName();
        }).collect(Collectors.toList());
        userDTO.setRoles(roles);
        return userDTO;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User registerNewUserAccount(UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("This email is already registered: " + accountDto.getEmail());
        }
        User user = new User();
        user.setFirstname(accountDto.getFirstname());
        user.setLastname(accountDto.getLastname());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String verificationToken) {
        VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public void saveRegisteredUser(final User user) {
        userRepository.save(user);
    }

    @Override
    public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(existingVerificationToken);
        verificationToken.updateToken(UUID.randomUUID()
                .toString());
        verificationToken = tokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
        .getTime() - calendar.getTime()
                .getTime()) <= 0 ) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }
        user.setEnabled(true);
        userRepository.save(user);
        return TOKEN_VALID;
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
