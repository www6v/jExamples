package javacore.xml;

import com.thoughtworks.xstream.XStream;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class XmlParser {
    public static void main(String args[]) throws Exception {
        File f = new File("D:\\workspace\\jDemo\\src\\main\\java\\javacore\\xml\\admin.xml");
        InputStream xmlInputStream = new FileInputStream(f);

        XStream xStream = new XStream();
        xStream.ignoreUnknownElements();
        xStream.alias("ConfigUser", ConfigUsers.class);
        xStream.alias("AdminUser", AdminUser.class);
        ConfigUsers users = (ConfigUsers) xStream.fromXML(xmlInputStream);
        List<AdminUser> adminUsers = users.getUsers();

        System.out.print(adminUsers);
    }
}