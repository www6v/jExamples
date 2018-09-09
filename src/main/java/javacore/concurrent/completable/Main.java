package javacore.concurrent.completable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
//import org.apache.commons.lang3.time.StopWatch;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    private  static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();
    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }
    public static void main(String[] args) throws Exception {
//        whenComplete();
//        thenCompose();

        thenCompose1();
    }

    private static void thenCompose1() throws InterruptedException, java.util.concurrent.ExecutionException {
        StopWatch watch=new  StopWatch();

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            task1(watch, "task1", "task1 doing...");
            return "result1";
        });

        // 等第一个任务完成后，将任务结果传给参数result，执行后面的任务并返回一个代表任务的completableFuture
        CompletableFuture<String> completableFuture2= completableFuture1.thenCompose(result->
                CompletableFuture.supplyAsync(()->{
                         task1(watch, "task2", "task2 doing...");
                         //返回结果
                         return "result2";
        }));

        System.out.println(completableFuture2.get());

        logger.error( watch.prettyPrint());
        logger.error("sw.getTotalTimeMillis()" + watch.getTotalTimeMillis());
        logger.error("sw.getTaskCount()" + watch.getTaskCount());
    }

    private static void task1(StopWatch watch, String taskName, String x) {
        watch.start(taskName);

        System.out.println(x);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        watch.stop();
    }

    private static void thenCompose() throws InterruptedException, java.util.concurrent.ExecutionException {
        /////
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> ff =  future1.thenCompose( i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "";
            });
        });
        System.out.println(ff.get()); //1000
    }

    private static void whenComplete() throws InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::getMoreData);
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println(v); /// value
            System.out.println(e); /// error
        });
        System.out.println(f.get());
    }
}