package br.com.api.g4.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	@Column(nullable = false, unique = true)
	private Integer id;
	@Column( length = 60)
	@NotBlank
	private String nome;
	private String descricao;
	@Column( length = 10)
	@NotBlank
	private LocalDate dataFabricacao;
	@Column()
	@NotBlank
	private Integer qntdEstoque;
	@Column()
	@NotBlank
	private Double valorUnitario;
	@Column()
	@NotBlank
	private Boolean ativo;

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, String descricao, LocalDate dataFabricacao, Integer qntdEstoque,
			Double valorUnitario, Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataFabricacao = dataFabricacao;
		this.qntdEstoque = qntdEstoque;
		this.valorUnitario = valorUnitario;
		this.ativo = ativo;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataFabricacao="
				+ dataFabricacao + ", qntdEstoque=" + qntdEstoque + ", valorUnitario=" + valorUnitario + ", ativo="
				+ ativo + "]";
	}

}