package br.com.api.g4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.g4.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);

}
