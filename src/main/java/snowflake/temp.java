package snowflake;

import java.lang.management.ManagementFactory;

public class temp {

	public static void main(String args[]) {
//		new temp().getPId();
		new temp().ttt();
	}
	 
	public  long getPId() {
		// get name representing the running Java virtual machine.    
		String name = ManagementFactory.getRuntimeMXBean().getName();    
		System.out.println(name);    
		// get pid    
		String pid = name.split("@")[0]; 
		System.out.println("Pid is:" + pid); 
		
		return Long.valueOf(pid);
	} 
	
	public void ttt() {
		String aa="Hello";
		int n=3;
		String b=aa.substring(aa.length()-n,aa.length());
		System.out.println(b);
	}	
}
