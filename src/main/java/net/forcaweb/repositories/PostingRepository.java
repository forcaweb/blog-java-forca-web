package net.forcaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.forcaweb.entities.Posting;

public interface PostingRepository extends JpaRepository<Posting, Long> {

}
