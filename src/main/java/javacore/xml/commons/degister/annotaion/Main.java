
package javacore.xml.commons.degister.annotaion;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.annotations.FromAnnotationsRuleModule;
import org.apache.commons.digester3.binder.DigesterLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author http://www.cnblogs.com/chenpi/
 * @version 2017年6月7日
 */
// not ok
public class Main
{
    public static void main(String[] args)
    {
        try
        {

            DigesterLoader loader = DigesterLoader.newLoader(new FromAnnotationsRuleModule()
            {
                @Override
                protected void configureRules()
                {
                    bindRulesFrom(Channel.class);
                }

            });

            Digester digester = loader.newDigester();

            File f = new File("D:\\workspace\\jDemo\\src\\main\\java\\javacore\\xml\\commons\\degister\\rss.xml");
            InputStream xmlInputStream = new FileInputStream(f);

            Channel channel = digester
                    .parse(xmlInputStream);

            System.out.println(channel.getTitle());
            System.out.println(channel.getImage().getDescription());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}