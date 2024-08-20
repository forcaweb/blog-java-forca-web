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
import net.forcaweb.entities.enums.RoleUser;
import net.forcaweb.repositories.CategoryRepository;
import net.forcaweb.repositories.PostingRepository;
import net.forcaweb.repositories.UserRepository;
import net.forcaweb.security.services.BcryptPasswordService;

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
	
	@Autowired
	private BcryptPasswordService bcryptPasswordService;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Notícias");
		Category cat2 = new Category(null, "Vendas");
		Category cat3 = new Category(null, "Patrocinio");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User u1 = new User(null, "Diego", "diego@teste.com", "999999999", LocalDate.parse("21/06/1992", fmt), bcryptPasswordService.encondePass("1234567"), RoleUser.ADMIN);
		User u2 = new User(null, "Thais", "thais@teste.com", "999999999", LocalDate.parse("06/03/1996", fmt), bcryptPasswordService.encondePass("1234567"), RoleUser.MEMBER);
		User u3 = new User(null, "Nicole", "nicole@teste.com", "999999999", LocalDate.parse("22/01/2002", fmt), bcryptPasswordService.encondePass("1234567"), RoleUser.GUEST);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Posting p1 = new Posting(null, "Primeiro posté", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u1);
		Posting p2 = new Posting(null, "Segundo poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u2);
		Posting p3 = new Posting(null, "Terceiro poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p4 = new Posting(null, "Quarto poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p5 = new Posting(null, "Quitno poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p6 = new Posting(null, "Sexto poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p7 = new Posting(null, "Sétimo poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p8 = new Posting(null, "Oitavo poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p9 = new Posting(null, "Nono poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p10 = new Posting(null, "Decimo poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p11 = new Posting(null, "Decimo primeiro poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p12 = new Posting(null, "Decimo segundo poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p13 = new Posting(null, "Decimo terceiro poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p14 = new Posting(null, "Decimo quarto poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		Posting p15 = new Posting(null, "Decimo quinto poste", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper neque vitae metus consectetur, eget hendrerit justo pretium. Vestibulum eget orci sollicitudin, finibus mi at, tempus augue. Nam semper tincidunt porttitor. Sed ut imperdiet nibh. Vestibulum sit amet pretium elit. Donec pulvinar vitae diam et gravida. Donec et justo a tortor convallis consequat. Phasellus sem sem, interdum id sem vel, cursus dapibus urna. Vivamus a enim ut tortor tempor consequat. Etiam ac massa turpis.", Instant.now(), u3);
		
		p1.setCategories(Arrays.asList(cat1));
		p2.setCategories(Arrays.asList(cat3));
		p3.setCategories(Arrays.asList(cat2, cat1));
		p4.setCategories(Arrays.asList(cat2, cat1));
		p5.setCategories(Arrays.asList(cat2, cat1));
		p6.setCategories(Arrays.asList(cat2, cat1));
		p7.setCategories(Arrays.asList(cat2, cat1));
		p8.setCategories(Arrays.asList(cat2, cat1));
		p9.setCategories(Arrays.asList(cat2, cat1));
		p10.setCategories(Arrays.asList(cat2, cat1));
		p11.setCategories(Arrays.asList(cat2, cat1));
		p12.setCategories(Arrays.asList(cat2, cat1));
		p13.setCategories(Arrays.asList(cat2, cat1));
		p14.setCategories(Arrays.asList(cat2, cat1));
		p15.setCategories(Arrays.asList(cat2, cat1));
		
		postingRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));
	
	}

}
