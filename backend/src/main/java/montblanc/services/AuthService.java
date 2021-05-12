package montblanc.services;

import montblanc.models.User;
import montblanc.models.dto.UserDTO;
import montblanc.exceptions.UserNotFoundException;
import montblanc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {

    @Autowired
    UserRepository userRepo;

    @Transactional(readOnly = true)
    public UserDTO login(String email, String password) throws UserNotFoundException {
        System.out.println("username : " + email);
        System.out.println("password : " + password);
        User user = userRepo.findByEmail(email);
        System.out.println(user);

        if (null == user) throw new UserNotFoundException();

        boolean passwordCorrect = BCrypt.checkpw(password, user.getPassword());
        System.out.println(user);
        if (!passwordCorrect) throw new UserNotFoundException();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        List<String> roles = user.getRoles().stream().map(r -> {
            return r.getName();
        }).collect(Collectors.toList());
        userDTO.setRoles(roles);
        return userDTO;
    }
}
