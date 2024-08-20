package net.forcaweb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.forcaweb.entities.Posting;
import net.forcaweb.services.PostingService;

@RestController
@RequestMapping(value = "/postings")
public class PostingResource {

	@Autowired
	private PostingService service;
	
	@GetMapping
	public ResponseEntity<Page<Posting>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		return ResponseEntity.ok(service.findAll(page, size, sortDirection));
	}
	
	@GetMapping(value = "/{title}")
	public ResponseEntity<Posting> findByTitle(@PathVariable String title){
		Posting ptns = service.findByTitle(title);
		return ResponseEntity.ok().body(ptns);
	}
}
