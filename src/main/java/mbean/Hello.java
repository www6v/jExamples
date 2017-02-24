package mbean;

import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.CompositeData;

public class Hello implements HelloMXBean  {   //  HelloMBean
  
    private Map map = new HashMap();
    
    private final String name;  
    private final int age;  
    private String email;  
    
    private Book book;   //  不可用  -> HelloMBean 
      
    public Hello(String name, int age, String email) {  
        this.name = name;  
        this.age = age;  
        this.email = email;  
    }  
  
    @Override  
    public int getAge() {  
        return age;  
    }  
  
    @Override  
    public String getName() {  
        return name;  
    }  
  
    @Override  
    public String getEmail() {  
        return email;  
    }  
  
    @Override  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    @Override  
    public String sayHello() {  
        return "Hello, I'm " + name + ".";  
    }

	@Override
	public Book getBook() {
		return this.book;
	}

	@Override
	public void addBook(Book book) {
		this.book = book;
	}

	@Override
	public Map getTestMap() {
        map.put("111", "222");
		return map;
	}

	@Override
	public void setTestMap(Map testMap) {	
		map.putAll(testMap);
	}
//
//	@Override
//	public void addTestMap(Map testMap) {
//		map.putAll(testMap);	
//	} 
//	
//	@Override
//	public void addTestMapToString(String key, String value) {
//		map.put(key, value);
//	}  	
}  