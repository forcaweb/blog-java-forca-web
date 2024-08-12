package net.forcaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.forcaweb.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
