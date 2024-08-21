package net.forcaweb.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.forcaweb.entities.Posting;
import net.forcaweb.entities.User;
import net.forcaweb.repositories.PostingRepository;
import net.forcaweb.repositories.UserRepository;

@Service
public class PostingService {
	
	@Autowired
	private PostingRepository repository;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<Posting> findAll(int page, int size, Direction sortDirection){
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "moment"));
		return repository.findAll(pageable);
	}
	
	public Posting findByTitle(String urlIdentify) {
		Posting obj = repository.findByUrlIdentify(urlIdentify);
		return obj;
	}
	
	public Posting findById(Long id) {
		Optional<Posting> obj = repository.findById(id);
		return obj.get();
	}

	public Posting insert(String token, Posting obj) {
		if(token != null && token.startsWith("Bearer ")) {
		token = token.substring(7);
		String user = service.findByToken(token);
		User userPosting = (User) userRepository.findByEmail(user);
		obj.setUserPosting(userPosting);
		obj.setMoment(Instant.now());
		obj = repository.save(obj);
		return obj;
		}
		return null;
	}
}
