package net.forcaweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.forcaweb.entities.Posting;
import net.forcaweb.repositories.PostingRepository;

@Service
public class PostingService {
	
	@Autowired
	private PostingRepository repository;
	
	public List<Posting> findAll(){
		return repository.findAll();
	}
	
	public Posting findById(Long id) {
		Optional<Posting> obj = repository.findById(id);
		return obj.get();
	}

}
