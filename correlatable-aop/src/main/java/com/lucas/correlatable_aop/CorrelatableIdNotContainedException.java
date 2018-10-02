package com.lucas.correlatable_aop;

public class CorrelatableIdNotContainedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CorrelatableIdNotContainedException() {
		super("No correlation is available to be retrieved. "
				+ "Correlation ids can only be retrieved in a chain "
				+ "of method calls started by a correlatable method.");
	}
	
}
