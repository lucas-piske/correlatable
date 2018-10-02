package com.lucas.correlatable_aop;

public interface CorrelatableIdAccessor {
	public String get() throws CorrelatableIdNotContainedException;
}
