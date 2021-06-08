package montblanc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import montblanc.Entity.Token;
import montblanc.repository.TokenRepository;
@Service
public class TokenService {
	@Autowired
	private TokenRepository tokenRepository;

	public Token findByToken(String token) {
		return tokenRepository.findByToken(token);
	}

	public void createToken(Token token) {
		Token localToken = tokenRepository.findByToken(token.getToken());

		if (localToken != null) {
			System.out.println("Le jeton :  " + token.getToken() + " est déjà existant!!");
		} else {
			localToken = tokenRepository.save(token);
		}
	}
}
