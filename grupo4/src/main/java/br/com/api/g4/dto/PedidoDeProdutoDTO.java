package br.com.api.g4.dto;

import java.util.List;

public class PedidoDeProdutoDTO {
	
	
	private List<ProdutoDePedidoDTO> idDoproduto;
	
	public PedidoDeProdutoDTO() {
		super();
	}
	
	public PedidoDeProdutoDTO(Integer quantidade, List<ProdutoDePedidoDTO> idDoproduto) {
		super();
		this.idDoproduto = idDoproduto;
	}



	public List<ProdutoDePedidoDTO> getIdDoproduto() {
		return idDoproduto;
	}

	public void setIdDoproduto(List<ProdutoDePedidoDTO> idDoproduto) {
		this.idDoproduto = idDoproduto;
	}
	
}