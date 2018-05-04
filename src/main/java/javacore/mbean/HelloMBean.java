package javacore.mbean;

import java.util.Map;

public interface HelloMBean {  
    public int getAge();  
    public String getName(); 
    
    public String getEmail();  
    public void setEmail(String email);
    
    public String sayHello();   // 能操作
    

    public Book getBook();  
    public void addBook(Book book);  // 不能操作
    
    public Map getTestMap();  /// 可以显示
    public void setTestMap(Map testMap);  
    
    public void addTestMap(Map testMap);  // 不能操作
    
	void addTestMapToString(String key, String value);  // 可以在map中加对象
}  