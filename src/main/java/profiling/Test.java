package profiling;

public class Test {

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        while (true) {
            test.func1();
            test.func2();
            test.func3();
        }
    }

    public void func1() throws Exception { //调用第一个方法，需要100ms
        Thread.sleep(100L);
    }

    public void func2() throws Exception { //调用第二个方法，需要500ms
        Thread.sleep(500L);
    }

    public void func3() throws Exception { //调用第三个方法，需要1500ms
        Thread.sleep(1500L);
    }

}
