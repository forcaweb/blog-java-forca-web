package net.forcaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.forcaweb.entities.Posting;

public interface PostingRepository extends JpaRepository<Posting, Long> {

	@Query("SELECT p FROM Posting p WHERE p.urlIdentify = :urlIdentify")
    Posting findByUrlIdentify(@Param("urlIdentify") String urlIdentify);
}
