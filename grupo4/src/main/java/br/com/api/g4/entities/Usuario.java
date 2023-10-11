package br.com.api.g4.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)//informa q é pk
		private Integer id;
		private String nome;
		private Boolean endereco;
		private String nome_usuario;
		private String email;
		private String cpf;
		private LocalDate data_nascimento;
}
