package net.forcaweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.forcaweb.entities.User;
import net.forcaweb.exceptions.DBExceptions;
import net.forcaweb.exceptions.ResourceNotFoundException;
import net.forcaweb.repositories.UserRepository;
import net.forcaweb.security.services.BcryptPasswordService;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BcryptPasswordService bcryptPasswordService;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		obj.setPassword(bcryptPasswordService.encondePass(obj.getPassword()));
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DBExceptions(e.getMessage());
		}
		
	}
}
