package mbean;

import java.util.Map;

public interface HelloMXBean {  
    public int getAge();  
    public String getName();  
    public String getEmail();  
    public void setEmail(String email);  
    public String sayHello();  
    
    public Book getBook();  
    public void addBook(Book book);  
    
    public Map getTestMap();  /// 显示为empty
	void setTestMap(Map testMap);
//	void addTestMap(Map testMap);
//	void addTestMapToString(String key, String value);
}