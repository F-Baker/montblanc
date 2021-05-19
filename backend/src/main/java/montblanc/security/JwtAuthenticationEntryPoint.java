package montblanc.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        final String expired = (String) request.getAttribute("expired");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Objects.requireNonNullElse(expired, "JWTAuthenticationEntryPoint: Invalid something or another"));
    }
}
