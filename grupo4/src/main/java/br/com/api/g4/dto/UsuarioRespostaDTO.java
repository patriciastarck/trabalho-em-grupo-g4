package br.com.api.g4.dto;

import java.util.List;

import br.com.api.g4.entities.Endereco;

public class UsuarioRespostaDTO {

	public String nome;
	public String nomeUsuario;
	public String email;
	private List<Endereco> endereco;

	public UsuarioRespostaDTO() {
		super();
	}

	public UsuarioRespostaDTO(String nome, String nomeUsuario, String email, List<Endereco> endereco) {
		super();
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.endereco = endereco;
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

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

}
