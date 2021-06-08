package montblanc.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import montblanc.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JWTService {

    private final Key secretKey;

    public JWTService() {
        this.secretKey = getSigningKey();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("bXktc2VjcmV0LWtleSBteS1zZWNyZXQta2V5IG15LXNlY3JldC1rZXkgbXktc2VjcmV0LWtleSBteS1zZWNyZXQta2V5IG15LXNlY3JldC1rZXkgbXktc2VjcmV0LWtleSBteS1zZWNyZXQta2V5IG15LXNlY3JldC1rZXkgbXktc2VjcmV0LWtleSBteS1zZWNyZXQta2V5");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getJWT(String login, UserDTO userDTO) {
        return Jwts.builder().setSubject(login).setExpiration(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES)))
                .claim("userDTO", userDTO).signWith(secretKey).compact();
    }

    public JWTResult checkAuthorization(String authorization) {
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            Jws<Claims> parsed = parser.parseClaimsJws(authorization);
            System.out.println("body : " + parsed.getBody().toString());
            String login = parsed.getBody().getSubject();
            System.out.println(login);
            System.out.println(parsed.getBody().get("userDTO"));
            Map<String, Object> userMap = (Map<String, Object>) parsed.getBody().get("userDTO");
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstname((String) userMap.get("firstname"));
            userDTO.setLastname((String) userMap.get("lastname"));
            userDTO.setEmail((String) userMap.get("email"));
            List<String> roles = (List<String>) userMap.get("roles");
            userDTO.setRoles(roles);
            return JWTResult.buildInfo(login, userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return JWTResult.buildFail();
        }
    }
}