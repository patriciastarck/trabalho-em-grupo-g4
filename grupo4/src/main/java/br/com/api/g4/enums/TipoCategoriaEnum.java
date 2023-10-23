package br.com.api.g4.enums;

public enum TipoCategoriaEnum {
	
	CATEGORIA_ELETRONICOS("ELETRONICOS"),
	CATEGORIA_MOVEIS("MOVEIS"),
	CATEGORIA_VESTUARIO("VESTUARIO"),
	CATEGORIA_AUTOMOVEIS("AUTOMOVEIS");

	private String tipo;

	TipoCategoriaEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
