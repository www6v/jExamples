package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by www6v on 2019/4/15.
 */
public class FreemarkerTest {
    public static void main(String args[]){
        try{
            Configuration configuration=new Configuration(Configuration.getVersion());

            configuration.setDirectoryForTemplateLoading(new File("D:\\workspaceJava\\jDemo\\src\\main\\resources"));

            configuration.setDefaultEncoding("utf-8");

            Template template = configuration.getTemplate("test.ftl");

            Map map=new HashMap();

            map.put("name", "张三 ");

            map.put("message", "欢迎来到神奇的品优购世界！");


//            Map data = new HashMap<>();
            List<Student> stuList = new ArrayList<Student>();
            stuList.add(new Student(1, "小米", 11, "北京昌平回龙观"));
            stuList.add(new Student(2, "小米2", 12, "北京昌平回龙观"));
            stuList.add(new Student(3, "小米3", 13, "北京昌平回龙观"));
            stuList.add(new Student(4, "小米4", 14, "北京昌平回龙观"));
            stuList.add(new Student(5, "小米5", 15, "北京昌平回龙观"));
            stuList.add(new Student(6, "小米6", 16, "北京昌平回龙观"));
            stuList.add(new Student(7, "小米7", 17, "北京昌平回龙观"));

//            data.put("stuList", stuList);
            map.put("stuList", stuList);


            List<RtcVar> rtcVarList = new ArrayList<RtcVar>();

            for(int i=1; i<=10; i++) {
                for(int j =1; j<=100; j++) {
                    for(int k =1; k<=100; k++) {
                        rtcVarList.add(new RtcVar( "app"+i, "room" + j, "stream"+k, "user" + i*j*k) );
//                        System.out.println("app"+ i +":" +"room" + j + ":" +"stream"+ k + ":" + "user");
                    }
                }
            }

            map.put("rtcVarList", rtcVarList);

            Writer out =new FileWriter(new File("d:\\kafkaPayload.txt"));

            template.process(map, out);

            out.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
