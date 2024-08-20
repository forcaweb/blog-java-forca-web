package net.forcaweb.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.forcaweb.repositories.UserRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		try {
		var token = this.recoverToken(request);
		if(token != null) {
			var email = tokenService.validateToken(token);
			UserDetails user = userRepository.findByEmail(email);
			var authorization = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authorization);
		}
		filterChain.doFilter(request, response);
        }catch(TokenExpiredException e) {
        	response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token expirado\", \"message\": \"O token expirou. Por favor, faça login novamente.\"}");
            return;
        }
	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null) return null;
		return authHeader.replace("Bearer ", "");
	}

}
