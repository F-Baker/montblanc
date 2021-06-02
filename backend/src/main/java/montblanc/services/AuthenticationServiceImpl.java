package montblanc.services;

import montblanc.exceptions.UserAlreadyExistException;
import montblanc.persistence.dto.UserDto;
import montblanc.persistence.entities.User;
import montblanc.security.SignInRequest;
import montblanc.security.SignUpRequest;
//import montblanc.persistence.dto.SignUpRequestMapper;
import montblanc.persistence.repositories.UserRepository;
import montblanc.security.jwt.JwtResponse;
import montblanc.security.jwt.JwtUtils;
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
//    private final SignUpRequestMapper signUpRequestMapper;
    private final JwtUtils jwtUtils;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     AuthenticationManager authenticationManager,
                                     JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
//        this.signUpRequestMapper = signUpRequestMapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, new Date()));
    }

//    @Override
//    public User signUp(SignUpRequest request) {
//        return null;
//    }

//    @Override
//    public User signUp(SignUpRequest request) {
//        if (userRepository.existByEmail((request.getEmail()))) {
//            throw new UserAlreadyExistException("This email is already registered.");
//        }
//
//        return Optional.of(request)
//                .map(userDto:: )
////                .map(signUpRequestMapper::toModel)
//                .map(userRepository::save)
//                .orElse(null);
////        //generate the token for the email
//    }
}
