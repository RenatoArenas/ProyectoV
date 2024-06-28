package com.cibertec.proyectov.dto;

import java.util.Map;

public class ResponseData<T> {
	private String message;
	private int status;
	private T data;
	private Map<String, String> error;
	
	
	
	public ResponseData(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}



	public ResponseData(String message, int status, Map<String, String> error) {
		this.message = message;
		this.status = status;
		this.error = error;
	}



	public ResponseData(String message, int status, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	public Map<String, String> getError() {
		return error;
	}



	public void setError(Map<String, String> error) {
		this.error = error;
	}
	
	
	
	
	
	
}
