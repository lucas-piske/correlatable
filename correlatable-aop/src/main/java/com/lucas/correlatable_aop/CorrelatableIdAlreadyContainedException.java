package com.lucas.correlatable_aop;

public class CorrelatableIdAlreadyContainedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CorrelatableIdAlreadyContainedException() {
		super("A correlatable id is already in use. "
				+ "Nested correlational method calls are not allowed");
	}
	
}
