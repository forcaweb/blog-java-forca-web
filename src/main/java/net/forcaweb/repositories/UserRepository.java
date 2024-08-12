package net.forcaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.forcaweb.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
