package com.efc.helpdesk.domain.enums;

public enum Perfil {

	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO"); // Cria objetos padrão
	
	private Integer codigo;
	private String descricao;
	
	
	private Perfil(Integer codigo, String descricao) { // Construtor privado
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static Perfil toEnum(Integer cod) {
		
		if (cod == null) 
		{
			return null; 
		}
		
		for (Perfil p : Perfil.values()) 
		{
			
			if (cod.equals(p.getCodigo()))
			{
				return p;
			}
			
		}
		
		throw new IllegalArgumentException("Perfil inválido");
		
	}
	
	public Integer getCodigo() {
		return codigo;
	}


	public String getDescricao() {
		return descricao;
	}
	
	
	
}
