package br.com.api.g4.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.api.g4.entities.Role;

public class UsuarioDTO {

	private String nome;
	private String nomeUsuario;
	private String email;
	private Set<Role> roles;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String cpf;
	private LocalDate dataNascimento;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String nome, String nomeUsuario, String email, String password, String cpf,
			LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
