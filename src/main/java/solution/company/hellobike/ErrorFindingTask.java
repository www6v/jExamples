package solution.company.hellobike;

import lombok.AllArgsConstructor;

import lombok.Getter;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.*;

import java.util.concurrent.CompletableFuture;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.stream.Collectors;


/////   问题找错- 描述错误原因

@Getter

@AllArgsConstructor

public class ErrorFindingTask {

//    private static final Logger logger = LogManager.getLogger(solution.company.hellobike.ErrorFindingTask.class);

    private Integer id;

    private String name;

    public static int getRequiredTaskCount() {

        int count = new Random().nextInt(10);

        return Optional.of(count).filter(i -> i > 5).orElse(getRequiredTaskCount());   /////////////////////////// stackover  flow

    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        ErrorFindingTask task = null;

        try {

            int requiredPersonCount = task.getRequiredTaskCount();

            List<ErrorFindingTask> tasks = getTaskListFromDb(requiredPersonCount);

            tasks.add(new ErrorFindingTask(111, null));

            Map<Integer, String> map = tasks.stream().collect(Collectors.toMap(ErrorFindingTask::getId, ErrorFindingTask::getName));

            List<CompletableFuture<Void>> asyncTasks = new ArrayList<>();

            map.forEach((id, name) -> {

                CompletableFuture<Void> t = CompletableFuture.runAsync(() -> {

                    CompletableFuture.runAsync(() -> System.out.println("今晚打老虎: id = " + id + ", name = " + name), executor);

                }, executor);

                asyncTasks.add(t);

            });

            CompletableFuture.allOf(asyncTasks.toArray(new CompletableFuture[0])).join();

            executor.shutdown();

        } catch (Exception e) {

//            logger.error("代码报错了: {}", e.getMessage());

        }

    }

    private static List<ErrorFindingTask> getTaskListFromDb(int requiredTaskCount) {

        try {

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        ErrorFindingTask[] tasks = new Random().ints(requiredTaskCount, 1, 11)

                .mapToObj(i -> new ErrorFindingTask(i, "error" + i)).toArray(ErrorFindingTask[]::new);

        return Arrays.asList(tasks);

    }

}