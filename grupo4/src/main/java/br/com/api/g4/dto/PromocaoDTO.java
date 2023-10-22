package br.com.api.g4.dto;

public class PromocaoDTO {
	
	private String nome;
	private Double valorUnitario;

	
	public PromocaoDTO() {
		super();
	}

	public PromocaoDTO(String nome, String valorUnitario) {
		super();
		this.nome = nome;
		this.valorUnitario = Double.valueOf(valorUnitario);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
