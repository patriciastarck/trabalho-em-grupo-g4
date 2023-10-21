package br.com.api.g4.dto;

import java.util.List;

import br.com.api.g4.entities.Produto;

public class CategoriaDTO {

	private String nome;
	private String descricao;
	private List<Produto> produtos;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(String nome, String descricao, List<Produto> produtos) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produtos = produtos;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
