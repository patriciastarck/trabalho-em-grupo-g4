package br.com.api.g4.dto;

public class ProdutoDePedidoDTO {

	private Integer id;
	private Integer quantidadePorProduto;
	
	public ProdutoDePedidoDTO() {
		super();
	}


	public ProdutoDePedidoDTO(Integer id, Integer quantidadePorProduto) {
		super();
		this.id = id;
		this.quantidadePorProduto = quantidadePorProduto;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getQuantidade() {
		return quantidadePorProduto;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidadePorProduto = quantidade;
	}
	
}
