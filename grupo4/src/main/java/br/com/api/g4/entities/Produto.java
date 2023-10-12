package br.com.api.g4.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	private String nome;
	private Boolean ativo;
	private String descricao;
	private LocalDate data_fabricacao;
	private Integer qntd_estoque;
	private Double valor_unitario;

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, Boolean ativo, String descricao, LocalDate data_fabricacao,
			Integer qntd_estoque, Double valor_unitario) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.descricao = descricao;
		this.data_fabricacao = data_fabricacao;
		this.qntd_estoque = qntd_estoque;
		this.valor_unitario = valor_unitario;
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

	public LocalDate getData_fabricacao() {
		return data_fabricacao;
	}

	public void setData_fabricacao(LocalDate data_fabricacao) {
		this.data_fabricacao = data_fabricacao;
	}

	public Integer getQntd_estoque() {
		return qntd_estoque;
	}

	public void setQntd_estoque(Integer qntd_estoque) {
		this.qntd_estoque = qntd_estoque;
	}

	public Double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", ativo=" + ativo + ", descricao=" + descricao
				+ ", data_fabricacao=" + data_fabricacao + ", qntd_estoque=" + qntd_estoque + ", valor_unitario="
				+ valor_unitario + "]";
	}

}
