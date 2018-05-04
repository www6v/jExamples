package javacore.simple.jmx.mxbean;

import java.util.Map;

public interface ZooMXBean {
     
//     public Tiger getTiger();
    public Map<Integer, Tiger> getTiger(); ///
//     public List<Tiger> getTiger(); ///
     public void addTiger(Tiger tiger);
     
     public String getZooName();
     
     public int getTigerCount();
 }
 