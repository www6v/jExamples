package solution.company.hellobike;


//编程题：
//        题目描述：有一个40G的大文件，文件内容都是标准的英文单词，单词和单词之间用标准的空格分割，请设计实现统计单词个数的功能。
//        要求：
//        1)运行环境：8核32G服务器
//        2)语言要求：java实现
//        3)稳定快速


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordCount {
    private static final int THREAD_COUNT=8;
    public static void main(String args[]) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        BufferedReader reader = new BufferedReader(new FileReader("bigfile.txt"));
        List<Future<Integer>> futures = new ArrayList<>();

        for( int i = 0; i < THREAD_COUNT; i++) {
            futures.add( executor.submit( new WordCountTask(reader) ) );
        }

        int totalWordCount = 0;
        for (Future<Integer> future: futures) {
            totalWordCount += future.get();
        }

        executor.shutdown();
        reader.close();

        System.out.println("total word count" + totalWordCount);
    }

    static class WordCountTask implements Callable<Integer> {
        private BufferedReader reader;
        public WordCountTask(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public Integer call() throws Exception {
            int wordCount = 0;
            String line;

            while( (line = reader.readLine()) != null ) {
                wordCount += line.split("\\s+").length;
            }
            return wordCount;
        }
    }
}