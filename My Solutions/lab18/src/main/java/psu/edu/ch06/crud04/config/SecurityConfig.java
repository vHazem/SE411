package psu.edu.ch06.crud04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import psu.edu.ch06.crud04.model.User;
import psu.edu.ch06.crud04.security.UserPrincipal;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())  // Disable CSRF as we are using stateless sessions
			.authorizeHttpRequests(auth -> auth
				.anyRequest().authenticated()  // Require authentication to all API endpoints
			)
			.httpBasic(basic -> {})  // Require basic authentication
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Use stateless sessions
			);
		
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder pwde) {
		UserDetails usera = new UserPrincipal(new User(1, "usera", pwde.encode("pwd123")));
		UserDetails userb = new UserPrincipal(new User(2, "userb", pwde.encode("pwd456")));
		
		return new InMemoryUserDetailsManager(usera, userb);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
