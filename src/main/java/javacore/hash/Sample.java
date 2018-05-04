package javacore.hash;

import java.util.concurrent.atomic.AtomicInteger;


// 用Murmur2做抽样
// 在com.yihaodian.monitor.util.MonitorJmsSendUtil里
public class Sample {
  public static void main(String args[]) {
	    AtomicInteger ai = new AtomicInteger(0);
	    
	    for(int i =0; i < 100000; i++ ) {
			int v = new Murmur2().hash32(i);
			if(v > 0 && v <= Integer.MAX_VALUE * 0.7){
				ai.getAndIncrement();
			}    	
	    }
	    
	    System.out.println(ai.get());
  }
}
