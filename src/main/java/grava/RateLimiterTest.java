package grava;

import java.util.concurrent.Executor;

import com.google.common.util.concurrent.RateLimiter;

// http://ifeve.com/guava-ratelimiter/
public class RateLimiterTest {
	//速率是每秒两个许可	  
  public static void main(String args[]) {
	  final RateLimiter rateLimiter = RateLimiter.create(10*1000.0);

	  System.out.println( rateLimiter.acquire(2*10*1000) );
	  System.out.println( rateLimiter.acquire() );
	  System.out.println( rateLimiter.acquire() );  
	  System.out.println( rateLimiter.acquire() ); 
  }
  
//  void submitTasks(List tasks, Executor executor) {
//      for (Runnable task : tasks) {
//          rateLimiter.acquire(); // 也许需要等待
//          executor.execute(task);
//      }
//  }
}
