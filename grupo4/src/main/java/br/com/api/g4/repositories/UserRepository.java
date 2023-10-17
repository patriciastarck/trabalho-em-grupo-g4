package br.com.api.g4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.g4.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}
