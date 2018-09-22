package javacore.collections;

import com.sun.org.apache.bcel.internal.generic.FLOAD;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangwei110 on 2018/8/23.
 */
public class CollectionTest {
    public static void main(String args[]){
//        listSort();
//        treeSet();
//        printList();
        jdk8Map();
    }

    private static void treeSet() {
        TreeSet ts = new TreeSet();

        ts.add( new Pair(321.347f, "11111") );
        ts.add( new Pair(798.493f, "12969") );
        ts.add( new Pair(123.456f, "12969") );

        System.out.println( ((Pair)ts.first()).getScore()  ) ;
        System.out.println( ((Pair)ts.last()).getScore() );
    }

    private static void listSort() {
        List<Float> scoreList = new ArrayList<>();

        scoreList.add( 321.347f );
        scoreList.add( 798.493f );
        scoreList.add( 123.456f );

        Collections.sort(scoreList);
        System.out.println(scoreList);
    }

    private static void printList() {
        List<Long> objects = new ArrayList<>();
        objects.add(12132L);
        objects.add(122L);
        objects.add(132L);
        objects.add(12L);

        System.out.print(objects);
    }

    private static void jdk8Map() {
        Map<Integer, String> HOSTING = new HashMap<>();
        HOSTING.put(1, "linode.com");
        HOSTING.put(2, "heroku.com");
        HOSTING.put(3, "digitalocean.com");
        HOSTING.put(4, "aws.amazon.com");
        String result = "";
        for (Map.Entry<Integer, String> entry : HOSTING.entrySet()) {
            if ("aws.amazon.com".equals(entry.getValue())) {
                result = entry.getValue();
            }
        }
        System.out.println("Before Java 8 : " + result);
        //Map -> Stream -> Filter -> String
        result = HOSTING.entrySet().stream()
        .filter(map -> "aws.amazon.com".equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());

        HOSTING.entrySet().stream()
                .filter(map -> "aws.amazon.com".equals(map.getValue())).forEach();
        System.out.println("With Java 8 : " + result);
    }
}
