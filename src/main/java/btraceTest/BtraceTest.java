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
						new BtraceTest().methodB();
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
		String s = "";
		for(int i=0; i<3; i++) {			
			System.out.println( "Thread: " + Thread.currentThread().getName() + ", i :" + i );	
			for( int j =0; j<10000; j++) {
				s +=  ("jjjjjjjjjjjjjjjjjjjjjjjjj = " + j);
			}
		}		
	}
	
	public void methodB() {
	   System.out.println( "methodB ");
	   throw  new RuntimeException("ttttt");
	}
}
