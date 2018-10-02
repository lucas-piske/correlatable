package com.lucas.correlatable_aop;

import java.util.Optional;

public class ThreadLocalCorrelatableIdContainerImpl implements CorrelatablenIdContainer {

	private static ThreadLocal<Optional<String>> threadLocalId;
	
	public ThreadLocalCorrelatableIdContainerImpl() {
		threadLocalId = new ThreadLocal<Optional<String>>();
		threadLocalId.set(Optional.<String>empty());
	}
	
	public String get() throws CorrelatableIdNotContainedException {
		if(threadLocalId.get().isPresent()) {
			return threadLocalId.get().get();
		} else {
			throw new CorrelatableIdNotContainedException();
		}
	}

	public void put(String id) throws CorrelatableIdAlreadyContainedException {
		if(id == null) {
			throw new IllegalArgumentException("A correlatable "
					+ "id cannot be null");
		}
		
		if(!threadLocalId.get().isPresent()) {
			threadLocalId.set(Optional.of(id));
		} else {
			throw new CorrelatableIdAlreadyContainedException();
		}
	}

	public void clear() {
		threadLocalId.set(Optional.<String>empty());
	}

}
