package montblanc.repositories;

import montblanc.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String confirmationToken);
    //todo: token save method here

    
}