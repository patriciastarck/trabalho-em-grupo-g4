package br.com.api.g4.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.api.g4.entities.Categoria;

public class ProdutoDTO {

	private String nome;
	private String descricao;
	private LocalDate dataFabricacao;
	private Integer qntdEstoque;
	private Double valorUnitario;
	private List<Categoria> categorias;
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(String nome, String descricao, LocalDate dataFabricacao, Integer qntdEstoque,
			Double valorUnitario, List<Categoria> categorias) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataFabricacao = dataFabricacao;
		this.qntdEstoque = qntdEstoque;
		this.valorUnitario = valorUnitario;
		this.categorias = categorias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public Integer getQntdEstoque() {
		return qntdEstoque;
	}

	public void setQntdEstoque(Integer qntdEstoque) {
		this.qntdEstoque = qntdEstoque;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
