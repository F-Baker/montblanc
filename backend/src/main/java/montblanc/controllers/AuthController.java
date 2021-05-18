package montblanc.controllers;


import montblanc.models.Email;
import montblanc.models.Token;
import montblanc.models.User;
import montblanc.models.dto.SignInRequest;
import montblanc.models.dto.SignUpRequest;
import montblanc.repositories.TokenRepository;
import montblanc.repositories.UserRepository;
import montblanc.services.AuthenticationServiceImpl;
import montblanc.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    Email email;

    @Autowired
    EmailService emailService;

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
        Token token = new Token(user);

        /* Préparation de l'email à envoyer pour la confirmation avec jeton */
        email.setTo(user.getEmail());
        email.setSubject("BricoPasCher--Inscription à compléter");
        // Template Thymeleaf pour l'envoie d'email
        email.setTemplate("email");

        // Variable de contexte url
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/";

        // Préparation des variables pour le template thymeleaf
        Map<String, Object> model = new HashMap<>();
        model.put("userIdentity",user.getLastname() + " " + user.getFirstname());
        model.put("appUrl", appUrl);
        model.put("urlToken", "confirm-account?token=");
        model.put("token", token.getToken());
        email.setModel(model);
        // Envoie d'email
        emailService.sendEmail();

        // Enregistrement du jeton pour user(one to one) dans la table jetons
        tokenRepository.save(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @RequestMapping("/confirm-account")
    public String confirmUserAccount(@RequestParam("token") String confirmationToken) {
        Token token = tokenRepository.findByToken(confirmationToken);
        String emailMessage = "Default message";
        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            if (user.getCreatedOn() == null) {
                user.setEnabled(true);
//                user.setCreatedOn(new Date());
//                userRepository.updateUser(user);
                emailMessage = "registration successful";
//                modelAndView.addObject("registerDone", "message de succès");
//                modelAndView.setViewName("users/public/login");
            } else {

//                modelAndView.addObject("badLink", "message d'échec");
//                modelAndView.setViewName("users/public/login");
            }
        } else {
//            modelAndView.addObject("badLink", , "message d'échec");
//            modelAndView.setViewName("users/public/login");
        }

        return emailMessage;
    }


}
