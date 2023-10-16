package br.com.api.g4.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	@Column(nullable = false, unique = true)
	private Integer id;
	@Column(nullable = false, length = 60)
	private String nome;
	private String descricao;
	@Column(nullable = false, length = 10)
	private LocalDate dataFabricacao;
	@Column(nullable = false)
	private Integer qntdEstoque;
	@Column(nullable = false)
	private Double valorUnitario;
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToMany
	@JoinColumn(name="produto_id")
	private List<Categoria> categorias; 

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, Boolean ativo, String descricao, LocalDate dataFabricacao,
			Integer qntdEstoque, Double valorUnitario) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.descricao = descricao;
		this.dataFabricacao = dataFabricacao;
		this.qntdEstoque = qntdEstoque;
		this.valorUnitario = valorUnitario;
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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", ativo=" + ativo + ", descricao=" + descricao
				+ ", dataFabricacao=" + dataFabricacao + ", qntdEstoque=" + qntdEstoque + ", valorUnitario="
				+ valorUnitario + "]";
	}
}