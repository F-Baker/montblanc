package com.felixbaker.montblanc.controller;

import com.felixbaker.montblanc.Entity.User;
import com.felixbaker.montblanc.dto.LoginDTO;
import com.felixbaker.montblanc.dto.UserDTO;
import com.felixbaker.montblanc.dto.UserRegisterDTO;
import com.felixbaker.montblanc.service.AuthService;
import com.felixbaker.montblanc.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private Validator validator;

    //todo: figure if this value is the one tripping up the jar deployment
    @Value("${app.clientUrl}")
    private String clientUrl;

    @Autowired
    JWTService jwtService;

    //	API
    @PostMapping("/signin")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            UserDTO userDTO = authService.login(loginDTO.getEmail(), loginDTO.getPassword());
            String res = jwtService.getJWT(userDTO.getEmail(), userDTO);
            userDTO.setAccessToken(res);
            return ResponseEntity.ok(userDTO);
        } catch (Exception ex) {
            System.err.println(ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userRegisterDTO, HttpServletRequest request, ModelAndView modelAndView) {
        User user = userRegisterDTOToUser(userRegisterDTO);
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        if (!validate.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            authService.create(user, request, modelAndView);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/confirm-account")
    public void confirmUserAccount(HttpServletResponse httpServletResponse, @RequestParam("token") String confirmationToken) {
        try {
            authService.confirmUserAccount(confirmationToken);
            httpServletResponse.setHeader("Location", clientUrl + "?msg=account activated");
            httpServletResponse.setStatus(302);
        } catch (Exception e) {
            System.err.println(e);
            httpServletResponse.setHeader("Location", clientUrl + "?msg=invalid token");
            httpServletResponse.setStatus(302);
        }
    }

//	Non-API
    private User userRegisterDTOToUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setEmail(userRegisterDTO.getEmail());
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setPassword(encoder.encode(userRegisterDTO.getPassword()));
        user.setAddress(userRegisterDTO.getAddress());
        user.setPostalCode(userRegisterDTO.getPostalCode());
        user.setCity(userRegisterDTO.getCity());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        return user;
    }
}
