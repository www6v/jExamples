package mbean;

public interface HelloMXBean {  
    public int getAge();  
    public String getName();  
    public String getEmail();  
    public void setEmail(String email);  
    public String sayHello();  
    
    public Book getBook();  
    public void addBook(Book book);  
}