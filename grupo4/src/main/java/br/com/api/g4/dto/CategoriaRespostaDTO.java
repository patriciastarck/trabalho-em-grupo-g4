package br.com.api.g4.dto;

public class CategoriaRespostaDTO {

	private String nome;
	private String descricao;
	private Boolean ativo;

	public CategoriaRespostaDTO() {
		super();
	}

	public CategoriaRespostaDTO(String nome, String descricao, Boolean ativo) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}