package br.com.api.g4.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.api.g4.enums.TipoRoleEnum;

@Entity
@Table(name = "roles")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private TipoRoleEnum name;

	public Role() {
		super();
	}

	public Role(Long id, @NotNull TipoRoleEnum name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRoleEnum getName() {
		return name;
	}

	public void setName(TipoRoleEnum name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
