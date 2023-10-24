package br.com.api.g4.dto;

import java.time.LocalDate;

public class ProdutoRespostaDTO {

	private LocalDate dataFabricacao;
	private String descricao;
	private Boolean ativo;
	private String nome;
	private Integer qntdEstoque;
	private Double valorUnitario;
	public ProdutoRespostaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProdutoRespostaDTO(LocalDate dataFabricacao, String descricao, Boolean ativo, String nome,
			Integer qntdEstoque, Double valorUnitario) {
		super();
		this.dataFabricacao = dataFabricacao;
		this.descricao = descricao;
		this.ativo = ativo;
		this.nome = nome;
		this.qntdEstoque = qntdEstoque;
		this.valorUnitario = valorUnitario;
	}
	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
}
