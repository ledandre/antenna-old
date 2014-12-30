package br.com.ledtom.antenna.model.security;

public class InvalidLoginDataException extends Exception {
	private static final long serialVersionUID = -8407360614445054853L;
	
	private final static String message = "Dados inválidos";
	
	public InvalidLoginDataException() {
		super(message);
	}
	
	public InvalidLoginDataException(String message) {
		super(message);
	}
	
	public InvalidLoginDataException(Throwable throwable) {
		super(throwable);
	}
	
	public InvalidLoginDataException(Throwable throwable, String message) {
		super(message, throwable);
	}
}