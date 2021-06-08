package montblanc.service;

import montblanc.Entity.Token;
import montblanc.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            System.out.println("Token number:  " + token.getToken() + "already exists.");
        } else {
            localToken = tokenRepository.save(token);
        }
    }
}
