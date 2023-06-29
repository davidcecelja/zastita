package hr.mev.zastita.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration

public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new StranicaHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler());

        httpSecurity.csrf().ignoringRequestMatchers("/h2-console/**");
        httpSecurity.headers()
                .frameOptions()
                .sameOrigin();
        return httpSecurity.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, lozinka, enabled "
                        + "from korisnik "
                        + "where email = ?")
                .authoritiesByUsernameQuery("select email, uloga "
                        + "from korisnik "
                        + "where email = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

