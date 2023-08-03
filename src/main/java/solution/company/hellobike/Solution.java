package solution.company.hellobike;



//        编程题：
//        题目描述： 假设有一家共享单车公司，管理着两轮共享单车的租借和归还。你需要设计一个简单的系统，模拟该公司的单车管理过程。系统应该包括两个功能：租借单车和归还单车。
//
//        要求：
//        假设公司有100辆单车可供租借。
//        每次租借和归还单车时，都需要输出租借/归还单车的用户ID。
//        用户ID可以是任意字符串，可以重复使用，用于区分不同用户。
//        租借和归还操作应该是并发执行的，即多个用户可以同时租借或归还单车。
//        系统需要考虑单车库存不足和归还多余单车的情况。
//        请使用Java编写代码，实现上述要求。你可以使用任意的Java类库和数据结构。
//        在代码中注释清晰解释你的设计思路，并确保代码可运行。


import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    private AtomicInteger availableBikes;
    private final int totalBikes;

    public Solution(int totalBikes) {
        this.totalBikes = totalBikes;
        this.availableBikes = new AtomicInteger(totalBikes);
    }

    public synchronized void rentBike(String userId) {
        if (availableBikes.get() > 0) {
            availableBikes.decrementAndGet();
            System.out.println("User " + userId + " rented a bike. Bikes left: " + availableBikes);
        } else {
            System.out.println("No bikes available for rent.");
        }
    }

    public synchronized void returnBike(String userId) {
        if (availableBikes.get() < totalBikes) {
            availableBikes.incrementAndGet();
            System.out.println("User " + userId + " returned a bike. Bikes available: " + availableBikes);
        } else {
            System.out.println("No space to return more bikes.");
        }
    }
}


