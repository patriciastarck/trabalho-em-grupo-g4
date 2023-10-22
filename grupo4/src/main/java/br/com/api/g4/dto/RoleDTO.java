package br.com.api.g4.dto;

import br.com.api.g4.enums.TipoRoleEnum;

public class RoleDTO {
	
	private TipoRoleEnum name;

	public RoleDTO() {
		super();
	}

	public RoleDTO(TipoRoleEnum name) {
		super();
		this.name = name;
	}

	public TipoRoleEnum getName() {
		return name;
	}

	public void setName(TipoRoleEnum name) {
		this.name = name;
	}

}
