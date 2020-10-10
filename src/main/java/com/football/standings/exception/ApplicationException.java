package com.football.standings.exception;

public class ApplicationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4935121949832163711L;
	public ApplicationException() {
	}
	public ApplicationException(String msg) {
		super(msg);
	}
}
