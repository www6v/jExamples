package javacore.jvm;

//        结果：
//        2
//        3
//        a=110,b=0
//        1
//        4
// https://mp.weixin.qq.com/s/Zhqy1U7talHSeplN8n0_4Q
public class StaticTest {

    public static void main(String[] args) {
        staticFunction();
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    static int b = 112;
}