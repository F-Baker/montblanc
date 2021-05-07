package montblanc.service;

import montblanc.Entity.User;
import montblanc.dto.SignInRequest;
import montblanc.dto.SignUpRequest;
import montblanc.dto.SignUpRequestMapper;
import montblanc.exception.UserExistsException;
import montblanc.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final SignUpRequestMapper signUpRequestMapper;
    private final JwtUtils jwtUtils;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     AuthenticationManager authenticationManager,
                                     SignUpRequestMapper signUpRequestMapper,
                                     JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.signUpRequestMapper = signUpRequestMapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User signUp(SignUpRequest request) {
        if (userRepository.existByEmail((request.getEmail()))) {
            throw new UserExistsException("This email is already registered.");
        }
        return Optional.of(request)
                .map(signUpRequestMapper::toModel)
                .map(userRepository::save)
                .orElse(null);
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JWTResult(jwt, new Date()));
    }
}
