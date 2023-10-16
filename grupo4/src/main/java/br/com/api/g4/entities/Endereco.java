package br.com.api.g4.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
    @Column(nullable = false, unique = true)
    private Integer id;
    @Column(length = 9, nullable = false)
    private String cep;
    @Column(length = 60,nullable = false)
    private String logradouro;
    private Character complemento;
    @Column(length = 30,nullable = false)
    private String bairro;
    @Column(length = 30,nullable = false)
    private String localidade;
    @Column(length = 2,nullable = false)
    private String uf;
    
    
	public Endereco() {
		super();
	}


	public Endereco(Integer id, String cep, String logradouro, Character complemento, String bairro, String localidade,
			String uf) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
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


	public Character getComplemento() {
		return complemento;
	}


	public void setComplemento(Character complemento) {
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


	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + "]";
	}
    
    
}
