package javacore.commonlang;

import org.apache.commons.beanutils.BeanUtils;

public class BeanutilsTest {
    public static void main(String args[]) throws Exception {
        String className="cn.itcast.domain.Person";

        Class clazz=Class.forName(className);

        Object bean=clazz.newInstance();

        BeanUtils.setProperty(bean, "name", "张三");

        BeanUtils.setProperty(bean, "age", "23");

        BeanUtils.setProperty(bean, "gender", "男");

        BeanUtils.setProperty(bean, "xxx", "XXX");

        System.out.println(bean);
    }
}
