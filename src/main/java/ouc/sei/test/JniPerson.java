package ouc.sei.test;

public class JniPerson {

	long nativePerson;
	
	public static void main(String[] args){

//		System.setProperty("java.library.path","D:\\workspace\\testProject\\src\\main\\java\\ouc\\sei\\test\\");
//		System.out.println( System.getProperty("java.library.path") );
//		System.loadLibrary("libwriteutfLib");
		System.load("D:\\workspace\\testProject\\src\\main\\java\\ouc\\sei\\test\\libwriteutfLib.dll");
		
		JniPerson jperson = new JniPerson();
		jperson.initPerson(20, "Tonny");
		jperson.sayInfo();
		jperson.writeFile("text.txt", "helloJni");
	}
	public JniPerson(){
		nativePerson = createNativeObject();
	}
	public void initPerson(int age,String name){
		nativeInitPerson(nativePerson, age, name);
	}
	public void sayInfo(){
		nativeSayInfo(nativePerson);
	}
	public void writeFile(String pathName, String content){
		nativeWriteFile(nativePerson, pathName, content);
	}

	private native long createNativeObject();
	private native void nativeInitPerson(long personAddr,int age,String name);
	private native void nativeSayInfo(long personAddr);
	private native  void nativeWriteFile(long personAddr, String filePathName, String content); 
}
