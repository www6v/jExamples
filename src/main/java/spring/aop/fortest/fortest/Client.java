package spring.aop.fortest.fortest;

import java.util.ArrayList;

import org.springframework.aop.framework.ProxyFactory;

public class Client {
  public static void main(String args[]) {
	  System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
	  
	  System.out.print(System.getProperty("user.dir"));
	  
	  ProxyFactory proxyFactory = new ProxyFactory();
	  
//	  ArrayList<Class> arrayList = new ArrayList<Class>();
//	  arrayList.add(Greeting.class);
//	  Class[] array = (Class[]) arrayList.toArray();
	  
	  Class[] interfaces=new Class[]{Greeting.class}; 
	  
	  proxyFactory.setInterfaces(interfaces);
	  proxyFactory.setTarget(new GreetingImpl());
	  proxyFactory.addAdvice(new GreetingBeforeAdvice());
	  proxyFactory.addAdvice(new GreetingAfterAdvice());
	  
	  Greeting greeting = (Greeting) proxyFactory.getProxy();
	  greeting.greeting();
	  
  }
}
