package br.com.api.g4.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	@Column(nullable = false,length = 40)
	private String nome;
	@Column(nullable = false, length = 20)
	private String nomeUsuario;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false, length = 11)
	private String cpf;
	@Column(nullable = false, length = 10)
	private LocalDate dataNascimento;
	@Column(nullable = false)
	private Boolean ativo;

	
	@OneToMany
	@JoinColumn(name="usuario_id")
	private List<Pedido> pedidos; 
	
	@OneToMany
	@JoinColumn(name="usuario_id")
	private List<Produto> produtos; 
	
	@OneToMany
	@JoinColumn(name="usuario_id")
	private List<Endereco> endereco;
	
	public Usuario() {
		super();
	}

	public Usuario(Integer id, String nome, Boolean ativo, String nomeUsuario, String email,
			String cpf, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
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
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", ativo=" + ativo + ", nomeUsuario="
				+ nomeUsuario + ", email=" + email + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}
	
	
	
	
}