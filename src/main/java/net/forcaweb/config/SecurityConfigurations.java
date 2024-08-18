package net.forcaweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.ResourceAccessException;

import net.forcaweb.config.security.SecurityFilter;
import net.forcaweb.exceptions.TokenExpiredExceptions;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations {

	@Autowired
	SecurityFilter securityFilter;

	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	private static final String[] PUBLIC_MATCHERS_GET = { "/postings/**", };

	private static final String[] PUBLIC_MATCHERS_POST = { "/users/**", "/auth/login/**", "/auth/register/**" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http){
		try {
		return http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authrize -> authrize
						.requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
						.requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
						.requestMatchers(PUBLIC_MATCHERS).permitAll()
						.anyRequest().authenticated()
				)
				.headers(headers -> headers.disable())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		}catch(Exception e) {
        	throw new TokenExpiredExceptions(e.getMessage());
        }
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		try {
			return authenticationConfiguration.getAuthenticationManager();
		} catch (Exception e) {
			throw new ResourceAccessException(e.getMessage());
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
