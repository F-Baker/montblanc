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
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.httpBasic().disable();
        http.formLogin().disable();
        http.addFilterBefore(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/signin").permitAll()
                .mvcMatchers(HttpMethod.POST, "/signup").permitAll()
                .mvcMatchers(HttpMethod.GET, "/user/**").access("hasRole('ROLE_USER')")
                .mvcMatchers(HttpMethod.POST, "/admin/**").access("hasRole('ROLE_ADMIN')")
                .mvcMatchers("/**").authenticated();
    }
}
