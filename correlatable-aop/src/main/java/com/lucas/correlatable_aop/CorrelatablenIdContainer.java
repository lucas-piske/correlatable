package com.lucas.correlatable_aop;

public interface CorrelatablenIdContainer extends CorrelatableIdAccessor {
	public void put(String id) throws CorrelatableIdAlreadyContainedException;
	public void clear() throws CorrelatableIdNotContainedException;
}
