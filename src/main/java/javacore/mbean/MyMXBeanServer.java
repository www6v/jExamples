package javacore.mbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MyMXBeanServer {  
    public static void main(String[] args) throws Exception {  
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();  
          
        ObjectName mbeanName = new ObjectName("me.clarenceau.jmx.mxbean:type=Hello");  
          
        Hello mbean = new Hello("ClarenceAu", 23, "ozhencong@gmail.com");  
        mbean.addBook(new Book("Thinking in Java", 99.0));  
        mbean.addBook(new Book("Design Pattern", 59.0));  
          
        server.registerMBean(mbean, mbeanName);  
          
        System.out.println("Wait ...");  
        Thread.sleep(Long.MAX_VALUE);  
    }  
} 