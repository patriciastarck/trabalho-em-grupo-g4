package br.com.api.g4.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.api.g4.entities.Produto;

public class PedidoDTO {

	private List<ProdutoDTO> produtos;
	

	public PedidoDTO() {
		super();
	}
 
	public PedidoDTO(LocalDate dataPedido, List<ProdutoDTO> produtos) {
		super();
		this.produtos = produtos;
	}


	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

}
