package montblanc.security.jwt;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class JwtResponse {

    private String token;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date generatedAt;

    @Override
    public String toString() {
        return "JWTResult{" +
                "token='" + token + '\'' +
                ", generatedAt=" + generatedAt +
                '}';
    }

    public JwtResponse() {
    }

    public JwtResponse(String token, Date generatedAt) {
        this.token = token;
        this.generatedAt = generatedAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Date generatedAt) {
        this.generatedAt = generatedAt;
    }
}