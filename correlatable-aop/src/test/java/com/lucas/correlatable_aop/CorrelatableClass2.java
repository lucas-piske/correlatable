package com.lucas.correlatable_aop;

import com.lucas.correlatable_aop.Correlatable;

public class CorrelatableClass2 {
	
	@Correlatable(idContainer="idContainer")
	public int return2() {
		return 2;
	}
	
}
