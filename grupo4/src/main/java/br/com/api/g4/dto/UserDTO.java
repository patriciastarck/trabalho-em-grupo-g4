package br.com.api.g4.dto;

import java.util.Set;

public class UserDTO {

    private String nomeUsuario;
    private String email;
    private Set<String> roles;
    private String password;
    
    private String cep;
	private String numero;
	private String complementoAdicional;

	public UserDTO() {
		super();
	}
	
	public UserDTO(String nomeUsuario, String email, Set<String> roles, String password, String cep, String numero,
			String complementoAdicional) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.roles = roles;
		this.password = password;
		this.cep = cep;
		this.numero = numero;
		this.complementoAdicional = complementoAdicional;
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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplementoAdicional() {
		return complementoAdicional;
	}

	public void setComplementoAdicional(String complementoAdicional) {
		this.complementoAdicional = complementoAdicional;
	}

}
