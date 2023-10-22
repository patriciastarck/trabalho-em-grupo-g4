package br.com.api.g4.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	@Column(nullable = false, unique = true)
	private Integer id;
	@Column( unique = true, length = 20)
	@NotBlank
	private String nome;
	@Column()
	@NotBlank
	private String descricao;
	@Column()
	@NotBlank
	private Boolean ativo;
	@OneToMany
	@JoinColumn(name = "categoria_id")
	private List<Produto> produtos;

	public Categoria() {
		super();
	}

	public Categoria(Integer id, String nome, String descricao, Boolean ativo, List<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.produtos = produtos;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", ativo=" + ativo + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
}