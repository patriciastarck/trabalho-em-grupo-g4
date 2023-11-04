package br.com.api.g4.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
    @Column(nullable = false, unique = true)
    private Integer id;
    @Column(length = 9)
    @NotBlank
    private String cep;
    @Column(length = 60)
    @NotBlank
    private String logradouro;
    @Column(length = 40)
    private String complemento;
    @Column(length = 30)
    @NotBlank
    private String bairro;
    @Column(length = 30)
    @NotBlank
    private String localidade;
    @Column(length = 2)
    @NotBlank
    private String uf;
    @Column()
//    @NotBlank
    private Boolean ativo;
    @Column(length = 10)
    @NotBlank
    private String numero;
    
    //@JsonProperty(access=JsonProperty.Access.READ_ONLY)

    
	public Endereco() {
		super();
	}

	public Endereco(Integer id, String cep, String logradouro, String complemento, String bairro, String localidade,
			String uf, Boolean ativo, String numero) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ativo = ativo;
		this.numero = numero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", ativo=" + ativo + ", numero="
				+ numero + "]";
	}
	
}