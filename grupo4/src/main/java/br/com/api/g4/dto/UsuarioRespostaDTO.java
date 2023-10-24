package br.com.api.g4.dto;

import java.util.List;

import javax.persistence.JoinColumn;

import br.com.api.g4.entities.Endereco;

public class UsuarioRespostaDTO {
	public String nome;
	public String nomeUsuario;
	public String email;
	public String cep;
	public String localidade;
	public String logradouro;
	public String bairro;
	public String numero;
	public String complemento;
	
	private List<Endereco> endereco;
	
	
	public UsuarioRespostaDTO() {
		
	}

	public UsuarioRespostaDTO(String nome, String nomeUsuario, String email, String cep, String localidade,
			String logradouro, String bairro, String numero, String complemento) {
		super();
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.cep = cep;
		this.localidade = localidade;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
}
