package testjvm.testclassloader;

public class Hot {
	public void hot() {
		System.out.println(" version 3 : " + this.getClass().getClassLoader());
	}
}
