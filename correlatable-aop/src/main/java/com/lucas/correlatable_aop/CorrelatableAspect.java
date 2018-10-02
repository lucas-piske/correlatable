package com.lucas.correlatable_aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Aspect
public class CorrelatableAspect {

	private static final CorrelatableIdFactory 
						defaultIdFactory = new UUIDCorrelableIdFactoryImpl();
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Around("@annotation(Correlatable)")
	public Object putAndClearCorrelatableId(
			ProceedingJoinPoint joinPoint) throws Throwable {
		
		Correlatable annotation = getAnnotation(joinPoint);
		CorrelatablenIdContainer idContainer = getIdContainer(annotation);
		CorrelatableIdFactory idFactory = getIdFactory(annotation);
		
		String newId = idFactory.produce();
		
		idContainer.put(newId);
		
		Object proceed = null;
		try {
			proceed = joinPoint.proceed();
		} finally {
			idContainer.clear();
		}
		
		return proceed;
	}
	
	private CorrelatableIdFactory getIdFactory(Correlatable annotation) {
		String idFactoryBeanName = annotation.idFactory();
		
		if(!idFactoryBeanName.isEmpty()) {
			return applicationContext.getBean(
					idFactoryBeanName,
					CorrelatableIdFactory.class);
		} else {
			return defaultIdFactory;
		}
	}
	
	private CorrelatablenIdContainer getIdContainer(
			Correlatable annotation) {		
		String containerIdBeanName = annotation.idContainer();
		
		CorrelatablenIdContainer idContainer = applicationContext.getBean(
				containerIdBeanName,
				CorrelatablenIdContainer.class);
		
		return idContainer;
	}
	
	private Correlatable getAnnotation(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		return method.getAnnotation(Correlatable.class);
	}
	
}
