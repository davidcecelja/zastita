package hr.mev.zastita.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()
	        	.authorizeHttpRequests((requests) -> requests
	            .requestMatchers("/korisnik/registracija", "/korisnik/prijava").permitAll()
	            .requestMatchers("/home").authenticated()
	            .anyRequest().authenticated()
	        )
	        .formLogin((form) -> form
	        	.loginPage(/tvojaputanja)
                .loginProcessingUrl(/tvojaputanja)
	            .defaultSuccessUrl("/home")
	            .permitAll()
	        );
	       
	    return http.build();
	}
}
