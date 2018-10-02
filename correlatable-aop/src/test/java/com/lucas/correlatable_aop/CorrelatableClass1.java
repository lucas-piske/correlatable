package com.lucas.correlatable_aop;

import com.lucas.correlatable_aop.Correlatable;
import com.lucas.correlatable_aop.CorrelatableIdAccessor;

public class CorrelatableClass1 {

	private final CorrelatableIdAccessor correlatableIdAccessor;
	private final CorrelatableClass2 correlatableClass2;
	private final NormalClass normalClass;
	
	public CorrelatableClass1(
			CorrelatableIdAccessor correlatableIdAccessor,
			CorrelatableClass2 correlatableClass2,
			NormalClass normalClass) {
		this.correlatableIdAccessor = correlatableIdAccessor;
		this.correlatableClass2 = correlatableClass2;
		this.normalClass = normalClass;
	}
	
	@Correlatable(idContainer="idContainer")
	public String returnCorrelationId() {
		normalClass.return1();
		return correlatableIdAccessor.get();
	}
	
	@Correlatable(idContainer="idContainer")
	public int invokeCorrelatableClass2Method() {
		return correlatableClass2.return2();
	}
	
	public void nonCorrelatableMethod() {
		correlatableIdAccessor.get();
	}
	
}
