package montblanc.services;

import montblanc.persistence.dto.UserDto;
import montblanc.persistence.entities.User;
import montblanc.persistence.entities.VerificationToken;

public interface UserService {

    User findUserByEmail(String email);

    User registerNewUserAccount(UserDto accountDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    VerificationToken getVerificationToken(String VerificationToken);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken generateNewVerificationToken(String token);

    String validateVerificationToken(String token);

}
