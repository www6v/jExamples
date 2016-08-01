package test;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyPoolSizeCalculator extends PoolSizeCalculator {  
  
 public static void main(String[] args) throws InterruptedException,   
                                               InstantiationException,   
                                               IllegalAccessException,  
                                               ClassNotFoundException {  
//  MyThreadSizeCalculator calculator = new MyThreadSizeCalculator();  
	 MyPoolSizeCalculator calculator = new MyPoolSizeCalculator(); 
     calculator.calculateBoundaries(new BigDecimal(1.0),   
                                 new BigDecimal(100000));  
 }  
  
 protected long getCurrentThreadCPUTime() {  
  return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();  
 }  
  
 protected Runnable creatTask() {  
	 return new Runnable() {

		public void run() {
			System.out.println("do something here");
//			for( int i = 0; i< 1000*1000 ; i++) { 
//				
//			}
            try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		 
	 };
//  return new AsynchronousTask(0, "IO", 1000000);  
 }  
   
 protected BlockingQueue createWorkQueue() {  
  return new LinkedBlockingQueue();  
 }  
  
}  