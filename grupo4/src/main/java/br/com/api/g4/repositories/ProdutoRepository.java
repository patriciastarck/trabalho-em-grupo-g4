package br.com.api.g4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.g4.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(value = "select count(*) from produto ", nativeQuery = true)
	public Integer contar();

	@Query(value = "select nome,valor_unitario from produto p order by random() limit 2", nativeQuery = true)
	public List<Object[]> promocao();
}
