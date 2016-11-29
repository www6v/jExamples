package btraceTest.classload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MyTest {

	public static void main(String[] args) throws IOException {
		java.util.Timer timer = new java.util.Timer();   

		TimerTask task = new TimerTask() {   
		public void run() {   
			try {
				MyTest mt = new MyTest();
				mt.loadAndInvoke();
				
				mt = null;
				System.gc();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}   
		};   
		  
		//以下是几种调度task的方法：   
		  
		timer.schedule(task, 1000, 5000)  ; 
		// time为Date类型：在指定时间执行一次。   
	}

	private  void loadAndInvoke() throws FileNotFoundException, IOException {
		// 读取本地的class文件内的字节码，转换成字节码数组
		File file = new File(".");
//		InputStream input = new FileInputStream(file.getCanonicalPath() + "\\bin\\samples\\Programmer.class");
		InputStream input = new FileInputStream("D:\\workspace\\testproject\\target\\classes\\btraceTest\\classload\\Programmer.class");
		
		byte[] result = new byte[1024];

		int count = input.read(result);
		// 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
		MyClassLoader loader = new MyClassLoader();
		Class clazz = loader.defineMyClass(result, 0, count);
		// 测试加载是否成功，打印class 对象的名称
		System.out.println(clazz.getCanonicalName());

		// 实例化一个Programmer对象
		Object o = null;
		try {
			o = clazz.newInstance();
			// 调用Programmer的code方法
			try {
				clazz.getMethod("code", null).invoke(o, null);
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}