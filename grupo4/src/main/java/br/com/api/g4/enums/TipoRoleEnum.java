package br.com.api.g4.enums;

public enum TipoRoleEnum {

	ROLE_USER("USUARIO"),
	ROLE_ADMIN("ADMINISTRADOR");

	private String tipo;

	TipoRoleEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
