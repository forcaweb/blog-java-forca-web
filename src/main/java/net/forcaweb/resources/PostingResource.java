package net.forcaweb.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
            @RequestParam(value = "direction", defaultValue = "desc") String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		return ResponseEntity.ok(service.findAll(page, size, sortDirection));
	}
	
	@GetMapping(value = "/{title}")
	public ResponseEntity<Posting> findByTitle(@PathVariable String title){
		Posting obj = service.findByTitle(title);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Posting> findByTitle(@PathVariable Long id){
		Posting obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Posting> insert(@RequestHeader("Authorization") String token ,@RequestBody Posting obj){
		var result = service.insert(token, obj);
		return ResponseEntity.ok(result);
	}
}
