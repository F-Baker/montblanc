package montblanc.persistence.repositories;

import montblanc.persistence.entities.User;
import montblanc.persistence.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String confirmationToken);

    VerificationToken findByUser(User user);
    
}
