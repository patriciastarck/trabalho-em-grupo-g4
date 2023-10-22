package br.com.api.g4.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	@NotNull
	@Size(max = 50)
	@NotBlank
	private String nome;
	@NotNull
	@Size(max = 100)
	@NotBlank
	private String descricao;
	private LocalDate dataFabricacao;
	private Integer qntdEstoque;
	private Double valorUnitario;
	private Boolean ativo;
	@ElementCollection
	@CollectionTable(
	    name = "pedido_produto",
	    joinColumns = @JoinColumn(name = "produto_id")
	)
	@MapKeyJoinColumn(name = "pedido_id")
	@Column(name = "quantidade")
	private Map<Pedido, Integer> itemQuantidade = new HashMap<>();

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, String descricao, LocalDate dataFabricacao,
			Integer qntdEstoque, Double valorUnitario, Boolean ativo,
			Map<Pedido, Integer> itemQuantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataFabricacao = dataFabricacao;
		this.qntdEstoque = qntdEstoque;
		this.valorUnitario = valorUnitario;
		this.ativo = ativo;
		this.itemQuantidade = itemQuantidade;
	}

	public Map<Pedido, Integer> getItemQuantidade() {
		return itemQuantidade;
	}

	public void setItemQuantidade(Map<Pedido, Integer> itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
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
				+ ativo + ", itemQuantidade=" + itemQuantidade + "]";
	}

}