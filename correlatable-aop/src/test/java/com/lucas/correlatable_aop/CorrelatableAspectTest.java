package com.lucas.correlatable_aop;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath*:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CorrelatableAspectTest {

	@Resource(name="correlatableClass1") 
	private CorrelatableClass1 correlatableClass1;
		
	@Test
	public void shouldReturnGeneratedCorrelationId() {
		String correlationId = correlatableClass1.returnCorrelationId();
		
		assertThat(correlationId,notNullValue());
		assertThat(correlationId.isEmpty(),is(false));
	}
	
	@Test
	public void shouldReturnDifferentIdsWhenMakingConsecutiveCalls() {
		String correlationId1 = correlatableClass1.returnCorrelationId();
		
		String correlationId2 = correlatableClass1.returnCorrelationId();
		
		assertThat(correlationId1,notNullValue());
		assertThat(correlationId1.isEmpty(),is(false));
		
		assertThat(correlationId2,notNullValue());
		assertThat(correlationId2.isEmpty(),is(false));
		
		assertThat(correlationId1,not(equalTo(correlationId2)));
	}
	
	@Test(expected=CorrelatableIdNotContainedException.class)
	public void shouldThrowExceptionWhenGettingIdOnNonCorrelationalMethod() {
		correlatableClass1.nonCorrelatableMethod();
	}
	
	@Test(expected=CorrelatableIdAlreadyContainedException.class)
	public void shouldThrowExceptionWhenCallingNestedCorrelatables() {
		correlatableClass1.invokeCorrelatableClass2Method();
	}
	
}
