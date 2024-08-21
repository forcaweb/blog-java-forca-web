package net.forcaweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.forcaweb.config.security.TokenService;
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
	
	@Autowired
	private TokenService jwUtil;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public String findByToken(String token){
		String obj = jwUtil.extractUsername(token);
		return obj;
	}
	
	public User insert(User obj) {
		try {
		obj.setPassword(bcryptPasswordService.encondePass(obj.getPassword()));
		return repository.save(obj);
		}catch(DataIntegrityViolationException e) {
			throw new DBExceptions("Prodibido usar mesmo e-mail para várias contas!");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DBExceptions("Proibido deletar uma conta associada a postagens!");
		}
	}
	
	public User update(User obj, Long id){
		try {
			User entity = repository.getReferenceById(id);
			updateData(obj, entity);
			return repository.save(entity);
		}catch(DataIntegrityViolationException e) {
			throw new DBExceptions("Error ao atualizar essa conta!");
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User obj, User entity) {
		entity.setName(obj.getName());
		entity.setPhone(obj.getPhone());
		entity.setBirthDay(obj.getBirthDay());
	}
}
