package com.felixbaker.montblanc.controller;

import com.felixbaker.montblanc.dto.UserFullDTO;
import com.felixbaker.montblanc.exception.UserNotFoundException;
import com.felixbaker.montblanc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<UserFullDTO> getUser(@RequestParam("email") String email, HttpServletRequest request) {
        UserFullDTO userDTO;
        try {
            userDTO = userService.getUser(email);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
