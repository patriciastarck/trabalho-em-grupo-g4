package br.com.api.g4.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	@Column(nullable = false, unique = true)
	private Integer id;
	@Column(nullable = false, unique = true, length = 20)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private Boolean ativo;

	public Categoria() {
		super();
	}

	public Categoria(Integer id, Boolean ativo, String nome, String descricao) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", ativo=" + ativo + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
}