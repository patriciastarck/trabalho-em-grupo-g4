package br.com.api.g4.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	private LocalDate dataPedido;
	private Boolean ativo;
	@ManyToMany // relacionamento muitos pra muitos
	@JoinTable( // cria a tabela de ligacao
			name = "pedido_produto", // nomeia a tabela de ligacao
			joinColumns = @JoinColumn(name = "pedido_id"), // referencia de chave estrangeira da tabela atual(pedido)
			inverseJoinColumns = @JoinColumn(name = "produto_id") // referencia de chave estrangeira da tabela de
																	// associacao(produto)
	)
	private List<Produto> produtos;

	public Pedido() {
		super();
	}

	public Pedido(Integer id, LocalDate dataPedido, Boolean ativo, List<Produto> produtos,
			List<Integer> quantidadePorProduto) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.ativo = ativo;
		this.produtos = produtos;
//		this.quantidadePorProduto = quantidadePorProduto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	
	@Override
	public String toString() {
		return "Pedido [ produtos=" + produtos + "]";
	}

	

}