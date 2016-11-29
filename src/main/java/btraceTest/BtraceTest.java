package btraceTest;

public class BtraceTest {
	public static void main(String args[]) {
		
		while (true) {
			
			try {
				Thread.sleep(1000*5);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			new Thread() {
				public void run() {
					System.out.println("main");
					try {
						new BtraceTest().methodA();
						Thread.sleep(1000);
						interrupt();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
	
	public void methodA() {
		for(int i=0; i<3; i++) {			
			System.out.println( "Thread: " + Thread.currentThread().getName() + ", i :" + i );	
		}		
	}
}
