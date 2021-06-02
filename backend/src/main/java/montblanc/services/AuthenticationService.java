package montblanc.services;

import montblanc.persistence.entities.User;
import montblanc.security.SignInRequest;
import montblanc.security.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

//    User signUp(SignUpRequest request);

    ResponseEntity<?> signIn(SignInRequest request);
}
