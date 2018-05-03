package spring.aop.fortest.fortest;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;


public class GreetingAfterAdvice implements AfterReturningAdvice {


	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
      System.out.print("after");		
	}

}
