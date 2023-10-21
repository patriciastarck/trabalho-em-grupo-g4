package br.com.api.g4.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.api.g4.entities.Produto;

public class PedidoDTO {

	private List<Produto> produtos;
	

	public PedidoDTO() {
		super();
	}

	public PedidoDTO(LocalDate dataPedido, List<Produto> produtos) {
		super();
		this.produtos = produtos;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
