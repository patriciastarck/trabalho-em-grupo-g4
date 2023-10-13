package br.com.api.g4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.g4.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
	
	@Query(value="select count(*) from pedido ", nativeQuery=true)
    public Integer contar();
}
