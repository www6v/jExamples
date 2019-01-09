package javacore.xml.test;

import com.thoughtworks.xstream.XStream;
import javacore.xml.AdminUser;
import javacore.xml.ConfigUsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class XmlParser1 {
    public static void main(String args[]) throws Exception {
        File f = new File("D:\\work\\商飞项目\\接线\\xml\\PH_8850C10300G21-copy.xml");
        InputStream xmlInputStream = new FileInputStream(f);

        XStream xStream = new XStream();
        xStream.ignoreUnknownElements();
        xStream.alias("out", Out.class);
        xStream.alias("PhysicalHarness", PhysicalHarness.class);
        xStream.alias("WireHarness", WireHarness.class);
        xStream.alias("WiresExtremitiesList",WiresExtremitiesList.class);
        xStream.alias("WireExtremity", WireExtremity.class);

        Out out = (Out) xStream.fromXML(xmlInputStream);
        PhysicalHarness physicalHarness = out.getPhysicalHarness();
        WiresExtremitiesList wiresExtremitiesList = physicalHarness.getWireHarness().getWiresExtremitiesList();

//        List<WireExtremity> wireExtremity = wiresExtremitiesList.getWireExtremity();
//        System.out.print(wireExtremity.size());
        System.out.print("t");
    }
}