package net.forcaweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.forcaweb.entities.Posting;
import net.forcaweb.repositories.PostingRepository;

@Service
public class PostingService {
	
	@Autowired
	private PostingRepository repository;
	
	public Page<Posting> findAll(int page, int size, Direction sortDirection){
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
		return repository.findAll(pageable);
	}
	
	public Posting findByTitle(String urlIdentify) {
		Posting obj = repository.findByUrlIdentify(urlIdentify);
		return obj;
	}

}
