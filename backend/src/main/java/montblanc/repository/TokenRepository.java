package montblanc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import montblanc.Entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{

	Token findByToken(String token);

}
