package montblanc.controllers;

import montblanc.models.User;
import montblanc.models.dto.SignInRequest;
import montblanc.models.dto.SignUpRequest;
import montblanc.services.AuthenticationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    public AuthController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        return authenticationServiceImpl.signIn(request);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        User user = authenticationServiceImpl.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
