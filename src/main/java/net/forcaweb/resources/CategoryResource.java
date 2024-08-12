package net.forcaweb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.forcaweb.entities.Category;
import net.forcaweb.services.CategoryService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500"})
@RequestMapping(value = "/categories/")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
}
