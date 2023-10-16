package br.com.api.g4.dto;

public class EnderecoDTO {
	
	private String cep;
	private String complemento;
	
	public EnderecoDTO() {
		super();
	}
	
	public EnderecoDTO(String cep, String complemento) {
		super();
		this.cep = cep;
		this.complemento = complemento;
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
	
	
}
