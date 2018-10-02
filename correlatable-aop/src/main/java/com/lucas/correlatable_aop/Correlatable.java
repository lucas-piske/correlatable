package com.lucas.correlatable_aop;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ METHOD, CONSTRUCTOR })
public @interface Correlatable {
	public String idContainer() default "correlationIdContainer";
	public String idFactory() default "";
}
