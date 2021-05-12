package montblanc.services;

import montblanc.models.User;
import montblanc.models.dto.SignInRequest;
import montblanc.models.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    User signUp(SignUpRequest request);

    ResponseEntity<?> signIn(SignInRequest request);
}
