package btrace;

// http://learnworld.iteye.com/blog/1402763 
import java.util.Random;  
  
public class BTraceTest1 {  
  
    public static void main(String[] args) throws Exception {  
        Random random = new Random();  
  
        // 计数器  
        Counter counter = new Counter();  
        while (true) {  
            // 每次增加随机值  
            counter.add(random.nextInt(10));  
            Thread.sleep(1000);  
        }  
    }  
} 