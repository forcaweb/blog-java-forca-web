package net.forcaweb.exceptions;

public class TokenExpiredExceptions extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TokenExpiredExceptions(String msg) {
		super(msg);
	}

}
