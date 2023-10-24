package br.com.api.g4.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.api.g4.entities.Produto;

public class PedidoRespostaDTO {

	private LocalDate dataPedido;
	private Boolean ativo;
	private List<Produto> produtos;

	public PedidoRespostaDTO() {
		super();
	}

	public PedidoRespostaDTO(LocalDate dataPedido, Boolean ativo, List<Produto> produtos) {
		super();
		this.dataPedido = dataPedido;
		this.ativo = ativo;
		this.produtos = produtos;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
