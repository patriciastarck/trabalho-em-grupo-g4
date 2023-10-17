package br.com.api.g4.entities;

	
	import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

	@Entity
	@Table(name="user")
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idUser;

	    private String nomeUsuario;
	    
	    private String email;
	    
	    @OneToOne
		@JoinColumn(name = "endereco_id")
		private Endereco endereco;
	    
	    @ManyToMany
	    @JoinTable(
	            name = "usuario_role",
	            joinColumns = @JoinColumn(name = "usuario_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles;

	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    private String password;

		public User() {
			super();
		}

		public User(Integer idUser, String nomeUsuario, String email, Endereco endereco, Set<Role> roles, String password) {
			super();
			this.idUser = idUser;
			this.nomeUsuario = nomeUsuario;
			this.email = email;
			this.endereco = endereco;
			this.roles = roles;
			this.password = password;
		}

		public Integer getIdUser() {
			return idUser;
		}

		public void setIdUser(Integer idUser) {
			this.idUser = idUser;
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

		public Endereco getEndereco() {
			return endereco;
		}

		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}

		@Override
		public String toString() {
			return "User [idUser=" + idUser + ", nomeUsuario=" + nomeUsuario + ", email=" + email + ", endereco=" + endereco
					+ ", roles=" + roles + ", password=" + password + "]";
		}

	}

