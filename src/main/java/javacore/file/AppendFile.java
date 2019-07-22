package javacore.file;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.apache.commons.io.Charsets;

import java.io.File;
import java.io.IOException;

/**
 * Created by www6v on 2019/7/22.
 */
public class AppendFile {

    public static void main(String args[]){
        String path = "D:\\temp\\appendFile.txt";
        File file = new File(path);
        try {
//            Files.asCharSink(file, Charsets.UTF_8).write("hhhh");
//
//            String read = Files.asCharSource(file, Charsets.UTF_8).read();
//            System.out.println("读取: " + read);

            //追加内容
            Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write("\nhhhh2");
            Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write("\nabc");
            Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write("\nabc1");
            String read2 = Files.asCharSource(file, Charsets.UTF_8).read();
            System.out.println("读取: " + read2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
