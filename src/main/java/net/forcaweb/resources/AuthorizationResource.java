package net.forcaweb.resources;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.forcaweb.config.security.TokenService;
import net.forcaweb.entities.AuthenticationDTO;
import net.forcaweb.entities.LoginDTO;
import net.forcaweb.entities.RegisterDTO;
import net.forcaweb.entities.User;
import net.forcaweb.entities.enums.RoleUser;
import net.forcaweb.repositories.UserRepository;

@RestController
@RequestMapping("/auth/")
public class AuthorizationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login/")
	public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO data){
		var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = this.authenticationManager.authenticate(authenticationToken);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginDTO(token));
	}
	
	@PostMapping("/register/")
	public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO data){
		if(userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
		
		String ecryptPass = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(null, data.name(), data.email(), null, null, ecryptPass, RoleUser.MEMBER);
		User obj = userRepository.save(newUser);
		return ResponseEntity.ok().body(obj);
	}
}
