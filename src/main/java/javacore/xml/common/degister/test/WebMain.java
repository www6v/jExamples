
package javacore.xml.common.degister.test;

import javacore.xml.common.degister.ServletBean;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


// ok
public class WebMain
{

    public static void main(String[] args)
    {
        try
        {
            // 1、创建Digester对象实例
            Digester digester = new Digester();

            // 2、配置属性值
            digester.setValidating(false);

            // 3、push对象到对象栈

            // 4、设置匹配模式、规则
            digester.addObjectCreate("out/PhysicalHarness", "javacore.xml.common.degister.test.PhysicalHarness");
            digester.addObjectCreate("out/PhysicalHarness/WireHarness", "javacore.xml.common.degister.test.WireHarness");
            digester.addCallMethod("out/PhysicalHarness/WireHarness", "addWireHarness", 1);

//            digester.addObjectCreate("out/PhysicalHarness/WireHarness", "javacore.xml.common.degister.test.WireHarness");

//            digester.addCallMethod("web-app/servlet/servlet-name", "setServletName", 0);
//            digester.addCallMethod("web-app/servlet/servlet-class", "setServletClass", 0);
//            digester.addCallMethod("web-app/servlet/init-param", "addInitParam", 2);
//            digester.addCallParam("web-app/servlet/init-param/param-name", 0);
//            digester.addCallParam("web-app/servlet/init-param/param-value", 1);

            File f = new File("D:\\work\\商飞项目\\接线\\xml\\PH_8850C10300G21-copy.xml");
            InputStream xmlInputStream = new FileInputStream(f);

            // 5、开始解析
            PhysicalHarness servletBean = digester
                    .parse(xmlInputStream);

            System.out.println("servletBean.getServletName()" + servletBean.getWireHarness());

            // 6、打印解析结果
//            System.out.println("servletBean.getServletName()" + servletBean.getServletName());
//            System.out.println("servletBean.getServletClass()" + servletBean.getServletClass());
//            for(String key : servletBean.getInitParams().keySet()){
//                System.out.println(key + ": " + servletBean.getInitParams().get(key));
//            }

        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        catch (SAXException e)
        {

            e.printStackTrace();
        }

    }
}