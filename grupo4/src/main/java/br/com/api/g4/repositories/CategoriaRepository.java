package br.com.api.g4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.g4.entities.Categoria;
import br.com.api.g4.entities.Usuario;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	@Query(value="select count(*) from categoria ", nativeQuery=true)
    public Integer contar();

	@Query(value = "select * from categoria u where nome = :nome", nativeQuery = true)
	public Optional<Categoria> findByNome(@Param("nome") String nome);
}


