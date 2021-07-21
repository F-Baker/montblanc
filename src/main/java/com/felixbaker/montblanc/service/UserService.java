package com.felixbaker.montblanc.service;

import com.felixbaker.montblanc.Entity.User;
import com.felixbaker.montblanc.dto.UserFullDTO;
import com.felixbaker.montblanc.exception.UserNotFoundException;
import com.felixbaker.montblanc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public UserFullDTO getUser(String email) throws UserNotFoundException {
        User user = userRepo.findByEmail(email);
        UserFullDTO u = new UserFullDTO();
        if (null != user) {
            u.setId(user.getUserId());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setEmail(user.getEmail());
            u.setPhoneNumber(user.getPhoneNumber());
            u.setAddress(user.getAddress());
            u.setPostalCode(user.getPostalCode());
            u.setCity(user.getCity());
            u.setEnrollmentStatus(user.getEnrollmentStatus().getName());
            return u;
        } else {
            throw new UserNotFoundException();
        }
    }
}
