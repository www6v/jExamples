package javacore.file;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangwei110 on 2018/9/7.
 */
public class FileNameTest {
    private static final Set<String> suffixSet = new HashSet<>();
    static {
        suffixSet.add("jpg");
        suffixSet.add("png");
    }

     public static void  main(String args[]) {

         File file = new File("HelloWorld.png");
         String fileName = file.getName();
         String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
         System.out.println(suffix);

         if( suffixSet.contains(suffix) ) {
//             extractAndSave(file, targetId, appId);
             System.out.print("suffix");
         }
         else {
//             logger.info("Not standard  "  +  file.getName() + " does not extract");
             System.out.print("else");
         }
    }
}
