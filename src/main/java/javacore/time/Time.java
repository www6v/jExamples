package javacore.time;

import org.joda.time.DateTime;
/**
 * Created by www6v on 2019/4/14.
 */
public class Time {
    public static void main(String args[]) {
//        String date="2010-12-22T10:49:18+08:00";
        String date1= "2019-04-03T15:41:41+0800";
        String date= "2019-04-03T15:41:41.919+0800";
//        DateTime dt=new DateTime();
        DateTime dt=DateTime.parse(date);
        System.out.println(dt.getMillis());
        dt=DateTime.parse(date1);
        System.out.println(dt.getMillis());
    }
}
