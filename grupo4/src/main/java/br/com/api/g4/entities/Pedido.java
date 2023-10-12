package br.com.api.g4.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	private LocalDate data_pedido;
	private Boolean ativo;	
	
	
	public Pedido() {
		super();
	}
	public Pedido(Integer id, LocalDate data_pedido, Boolean ativo) {
		super();
		this.id = id;
		this.data_pedido = data_pedido;
		this.ativo = ativo;
	}
	
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data_pedido=" + data_pedido + ", ativo=" + ativo + "]";
	}
	
	

}
