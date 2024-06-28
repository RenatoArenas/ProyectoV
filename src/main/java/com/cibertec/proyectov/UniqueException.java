package com.cibertec.proyectov;

public class UniqueException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String property;
	private String message;

    public UniqueException() {}

    public UniqueException(String property, String msg) {
        super(msg);
        this.property = property;
        this.message = msg;
    }

	public String getProperty() {
		return property;
	}

    
}