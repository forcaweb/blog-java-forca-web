package net.forcaweb.exceptions;

public class ResourceErrorInternalServerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceErrorInternalServerException(Object id) {
		super("Recurso não encontrado. ID = " + id);
	}

}
