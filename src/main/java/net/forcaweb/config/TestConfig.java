package net.forcaweb.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import net.forcaweb.entities.Category;
import net.forcaweb.entities.Posting;
import net.forcaweb.entities.User;
import net.forcaweb.repositories.CategoryRepository;
import net.forcaweb.repositories.PostingRepository;
import net.forcaweb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private PostingRepository postingRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Notícias");
		Category cat2 = new Category(null, "Vendas");
		Category cat3 = new Category(null, "Patrocinio");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User u1 = new User(null, "Diego", "diego@teste.com", "999999999", LocalDate.parse("21/06/1992", fmt));
		User u2 = new User(null, "Thais", "thais@teste.com", "999999999", LocalDate.parse("06/03/1996", fmt));
		User u3 = new User(null, "Nicole", "nicole@teste.com", "999999999", LocalDate.parse("22/01/2002", fmt));
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Posting p1 = new Posting(null, "Titulo aqui", "Texto aqui!", Instant.now(), u1);
		Posting p2 = new Posting(null, "Titulo aqui", "Texto aqui!", Instant.now(), u2);
		Posting p3 = new Posting(null, "Titulo aqui", "Texto aqui!", Instant.now(), u3);
		
		p1.setCategories(Arrays.asList(cat1));
		p2.setCategories(Arrays.asList(cat3));
		p3.setCategories(Arrays.asList(cat2, cat1));
		
		postingRepository.saveAll(Arrays.asList(p1, p2, p3));
	
	}

}
