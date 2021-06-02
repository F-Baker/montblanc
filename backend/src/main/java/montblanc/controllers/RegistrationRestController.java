package montblanc.controllers;


import montblanc.persistence.entities.Email;
import montblanc.persistence.entities.VerificationToken;
import montblanc.persistence.entities.User;
import montblanc.security.SignInRequest;
import montblanc.security.SignUpRequest;
import montblanc.persistence.repositories.VerificationTokenRepository;
import montblanc.persistence.repositories.UserRepository;
import montblanc.services.AuthenticationServiceImpl;
import montblanc.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

//    @Autowired
//    Email email;

//    @Autowired
//    EmailService emailService;

    private final AuthenticationServiceImpl authenticationServiceImpl;

    public RegistrationRestController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        return authenticationServiceImpl.signIn(request);
    }

//    @PostMapping(value = "/signup")
//    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
//
//        User user = authenticationServiceImpl.signUp(request);
//        VerificationToken verificationToken = new VerificationToken(user);
//
//        email.setTo(user.getEmail());
//        email.setSubject("Please verify your MBE account.");
//        email.setTemplate("email");
//
//        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/";
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("userIdentity",user.getLastname() + " " + user.getFirstname());
//        model.put("appUrl", appUrl);
//        model.put("urlToken", "confirm-account?token=");
//        model.put("token", verificationToken.getToken());
//        email.setModel(model);
//        emailService.sendEmail(email.getTo(), email.getSubject(), email.getTemplate());
//        verificationTokenRepository.save(verificationToken);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }

//    @RequestMapping("/confirm-account")
//    public String confirmUserAccount(@RequestParam("token") String confirmationToken) {
//        VerificationToken verificationToken = verificationTokenRepository.findByToken(confirmationToken);
//        String emailMessage = "emailMessage default message";
//        if (verificationToken != null) {
//            User user = userRepository.findByEmail(verificationToken.getUser().getEmail());
//            if (user.getCreatedOn() == null) {
//                user.setEnabled(true);
////                user.setCreatedOn(new Date());
////                userRepository.updateUser(user);
//                emailMessage = "registration successful";
////                modelAndView.addObject("registerDone", "message de succès");
////                modelAndView.setViewName("users/public/login");
//            } else {
//
////                modelAndView.addObject("badLink", "message d'échec");
////                modelAndView.setViewName("users/public/login");
//            }
//        } else {
////            modelAndView.addObject("badLink", , "message d'échec");
////            modelAndView.setViewName("users/public/login");
//        }
//
//        return emailMessage;
//    }
}
