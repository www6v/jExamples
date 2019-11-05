package javacore.collections;

import java.util.*;

/**
 * Created by www6v on 2019/10/31.
 */
public class CollectionTest1 {

    public static void main(String args[]){

        HashMap<String, List<UserDetail>> userMap = new HashMap<>();

        List<UserDetail> uList = new ArrayList<UserDetail>();
        UserDetail ud0 = new UserDetail();
        UserDetail ud1 = new UserDetail();
        UserDetail ud2 = new UserDetail();

        UserDetail ud5 = new UserDetail();
        UserDetail ud6 = new UserDetail();

        ud0.setStatusType(1);
        ud0.setTime( new Date().getTime());
        System.out.println("ud0:" + ud0.getTime() );

        ud1.setStatusType(1);
        ud1.setTime(new Date().getTime() + 678);
        System.out.println("ud1:" + ud1.getTime());

        ud2.setStatusType(1);
        ud2.setTime(new Date().getTime() + 890);
        System.out.println("ud2:" + ud2.getTime());


        ud5.setStatusType(3);
        ud5.setTime(new Date().getTime() + 1639);
        System.out.println("ud5:" + ud5.getTime());

        ud6.setStatusType(3);
        ud6.setTime(new Date().getTime() + 2078);
        System.out.println("ud6:" + ud6.getTime());

        uList.add(ud0);
//        uList.add(ud1);
//        uList.add(ud2);
        uList.add(ud5);
//        uList.add(ud6);

        userMap.put( "user1", uList);

        Map<String, List<UserDetail>> resultMap = new HashMap<>();
        userMap.forEach( (userId, list) -> {
            System.out.println("userId"+ userId);
            System.out.println("list" + list.size());

            List<UserDetail> l = new ArrayList<>();

            Comparator<UserDetail> comparator = (o1, o2) -> o1.compareTo(o2);
            Optional<UserDetail> min = list.stream().filter(x -> x.getStatusType() == 1).min(comparator);
            if(min != null && min.isPresent()){
                UserDetail userDetailMin = min.get();
                l.add(userDetailMin);
                System.out.println( "min:" + userDetailMin.getTime() );
            }

            Optional<UserDetail> max = list.stream().filter(x -> x.getStatusType() == 3).max(comparator);
            if(max != null && max.isPresent()) {
                UserDetail userDetailMax = max.get();
                l.add(userDetailMax);
                System.out.println( "max:" + userDetailMax.getTime() );
            }

            resultMap.putIfAbsent(userId, l);
        } );
    }
}
