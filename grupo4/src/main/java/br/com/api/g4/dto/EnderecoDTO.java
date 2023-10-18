package br.com.api.g4.dto;

public class EnderecoDTO {
	
	private String cep;
	private String complemento;
	private Boolean ativo;
	private String numero;
	
	public EnderecoDTO() {
		super();
	}
	
	public EnderecoDTO(String cep, String complemento, Boolean ativo, String numero) {
		super();
		this.cep = cep;
		this.complemento = complemento;
		this.ativo = ativo;
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}