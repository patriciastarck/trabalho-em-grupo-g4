package br.com.api.g4.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	private String nome;
	private Boolean endereco;
	private String nome_usuario;
	private String email;
	private String cpf;
	private LocalDate data_nascimento;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", nome_usuario=" + nome_usuario
				+ ", email=" + email + ", cpf=" + cpf + ", data_nascimento=" + data_nascimento + "]";
	}

	public Usuario(Integer id, String nome, Boolean endereco, String nome_usuario, String email, String cpf,
			LocalDate data_nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.nome_usuario = nome_usuario;
		this.email = email;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getEndereco() {
		return endereco;
	}

	public void setEndereco(Boolean endereco) {
		this.endereco = endereco;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
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

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

}
