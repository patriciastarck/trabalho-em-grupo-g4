package br.com.api.g4.entities;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class PedidoProdutoEntry {

	private LocalDateTime dataHora;
	private Integer quantidade;

	public PedidoProdutoEntry() {
		super();
	}

	public PedidoProdutoEntry(LocalDateTime dataHora, Integer quantidade) {
		super();
		this.dataHora = dataHora;
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "PedidoProdutoEntry [dataHora=" + dataHora + ", quantidade=" + quantidade + "]";
	}

}