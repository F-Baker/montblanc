package montblanc.config;

import montblanc.filter.JwtTokenAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenAuthFilter jwtTokenAuthFilter;

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println(bcryptPasswordEncoder().encode("ripodb"));
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.httpBasic().disable();
        http.formLogin().disable();
        http.addFilterBefore(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/public/**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
//			.mvcMatchers(HttpMethod.POST, "/api/employe/produits/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")
                .mvcMatchers(HttpMethod.POST, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")
                .mvcMatchers(HttpMethod.PUT, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")
                .mvcMatchers(HttpMethod.DELETE, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")
                .mvcMatchers("/api/**").authenticated();
    }
}
