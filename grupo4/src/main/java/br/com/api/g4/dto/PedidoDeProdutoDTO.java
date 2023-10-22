package br.com.api.g4.dto;

import java.util.List;

public class PedidoDeProdutoDTO {

	private List<ProdutoDePedidoDTO> produtos;

	public PedidoDeProdutoDTO() {
		super();
	}

	public PedidoDeProdutoDTO(List<ProdutoDePedidoDTO> produtos) {
		super();
		this.produtos = produtos;
	}

	public List<ProdutoDePedidoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDePedidoDTO> produtos) {
		this.produtos = produtos;
	}

}