package mbean;

public class Hello implements HelloMXBean {   // HelloMBean
    
    private final String name;  
    private final int age;  
    private String email;  
    
    private Book book;
      
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
}  