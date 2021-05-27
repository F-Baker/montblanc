package montblanc.services;

import montblanc.persistence.entities.User;
import montblanc.exceptions.UserNotFoundException;
import montblanc.persistence.repositories.UserRepository;
import montblanc.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public MyUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        return new MyUserDetails(user);
    }
}
