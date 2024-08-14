package net.forcaweb.security.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptPasswordService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public BcryptPasswordService() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}
	
	public String encondePass(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
	
	public boolean matchPass(String password, String encondedPassword) {
		return bCryptPasswordEncoder.matches(password, encondedPassword);
	}
}
