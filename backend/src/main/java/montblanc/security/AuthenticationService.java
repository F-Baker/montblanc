package montblanc.security;

import montblanc.entities.User;
import montblanc.utils.SignInRequest;
import montblanc.utils.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    User signUp(SignUpRequest request);

    ResponseEntity<?> signIn(SignInRequest request);
}
