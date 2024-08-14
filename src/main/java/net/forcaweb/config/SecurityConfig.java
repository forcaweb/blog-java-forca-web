package net.forcaweb.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {
	// Configuração para desenvolvimento
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/**").permitAll())
				.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
				.cors(cors -> cors.configurationSource(request -> {
					var corsConfig = new CorsConfiguration();
					corsConfig.setAllowedOrigins(List.of("*"));
					corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
					corsConfig.setAllowedHeaders(List.of("*"));
					corsConfig.setAllowCredentials(false);
					return corsConfig;
				}));

		return http.build();
	}

	/* Descomente este para produção. Não esqueça de informar as rotas.
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/users/**").permitAll().requestMatchers("/postings/**")
						.permitAll().requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated())
				.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/users/**", "/postings/**"))
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
				.cors(cors -> cors.configurationSource(request -> {
					var corsConfig = new CorsConfiguration();
					corsConfig.setAllowedOrigins(
							List.of("http://localhost:3000", "http://127.0.0.1:5500", "http://localhost:8080"));
					corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
					corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type"));
					corsConfig.setAllowCredentials(true);
					return corsConfig;
				}));

		return http.build();
	}*/
}
