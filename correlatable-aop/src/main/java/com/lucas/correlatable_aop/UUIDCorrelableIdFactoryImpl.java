package com.lucas.correlatable_aop;

import java.util.UUID;

public final class UUIDCorrelableIdFactoryImpl implements CorrelatableIdFactory {
	
	public String produce() {
		return UUID.randomUUID().toString();
	}

}
