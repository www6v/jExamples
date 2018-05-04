package javacore.simple.jmx.mxbean;
 
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
 
 public class ZooImpl implements ZooMXBean {
 
     private String zooName = " China zoo";
     private static List<Tiger> list;
     private static Map<Integer,Tiger> m;
     static {
         //初始化一只Tiger
         Tiger tiger = new Tiger(" the first tiger");
         list = new ArrayList<Tiger>();
         list.add(tiger);
         
         m = new HashMap<Integer, Tiger>(); ///
         m.put(0, tiger);
     }
     
     public void addTiger(Tiger tiger) { 
    	 Random random = new Random(); ///
         list.add(tiger);  
         m.put( random.nextInt() ,  tiger); ///
     }
 
//     public Tiger getTiger() { 
//         return list.get(0);
//     }

//     public List<Tiger> getTiger() { ///
//         return list;
//     }
     
     public Map<Integer, Tiger> getTiger() { /// 
         return m;
     }
     
     public int getTigerCount(){
         return list.size();
     }
     
     public String getZooName() {
         return zooName;
     }
     
     public String[] getAnimalNames(){
         return new String[]{"bird","tiger","mouse"};
     }

 }
 