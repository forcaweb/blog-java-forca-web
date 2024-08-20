package net.forcaweb.entities.enums;

public enum RoleUser {

	ADMIN(1, "Administrador"),
	MEMBER(2, "Membro");
	
	private int code;
	private String description;
	
	private RoleUser(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static RoleUser valueOF(int code) {
		for(RoleUser value : RoleUser.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código inválido!");
	}
}
