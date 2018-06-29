package es.wata.almansaj.wkapi.exceptions;

public class ElementNotFoundInDBException extends RuntimeException {

	private static final long serialVersionUID = -5584199756070892440L;

	public ElementNotFoundInDBException(String msg) {
		super(msg);
	}
	
}
