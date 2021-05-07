package montblanc.service;

import montblanc.Entity.User;
import montblanc.dto.SignInRequest;
import montblanc.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    User signUp(SignUpRequest request);

    ResponseEntity<?> signIn(SignInRequest request);
}
