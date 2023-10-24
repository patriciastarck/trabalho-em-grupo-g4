package br.com.api.g4.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	@NotNull
	@Size(max = 50)
	@NotBlank
	private String nome;
	@NotNull
	@Size(max = 50)
	@NotBlank
	private String nomeUsuario;
	@NotNull
	@NotBlank
	@Email
	private String email;
	@NotNull
	@Size(max = 11)
	@NotBlank
	private String cpf;
	private LocalDate dataNascimento;
	private Boolean ativo;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Pedido> pedidos;
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Produto> produtos;
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Endereco> endereco;
	@ManyToMany
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public Usuario() {
		super();
	}

	public Usuario(Integer id, String nome, String nomeUsuario, String email, String cpf, LocalDate dataNascimento,
			Boolean ativo, String password, List<Pedido> pedidos, List<Produto> produtos, List<Endereco> endereco,
			Set<Role> roles) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.ativo = ativo;
		this.password = password;
		this.pedidos = pedidos;
		this.produtos = produtos;
		this.endereco = endereco;
		this.roles = roles;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", nomeUsuario=" + nomeUsuario + ", email=" + email + ", cpf="
				+ cpf + ", dataNascimento=" + dataNascimento + ", ativo=" + ativo + ", password=" + password
				+ ", pedidos=" + pedidos + ", produtos=" + produtos + ", endereco=" + endereco + ", roles=" + roles
				+ "]";
	}
}