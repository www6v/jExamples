package ouc.sei.test.reco;

public class JniReco {
	long nativeCFeatExtractor;

	public static void main(String[] args){
		System.load("");

		JniReco jniReco = new JniReco();
		jniReco.initReco();
//		jniReco.initPerson(20, "Tonny");
//		jperson.sayInfo();
//		jperson.writeFile("text.txt", "helloJni");
	}
	public JniReco(){
		nativeCFeatExtractor = createNativeObject();
	}
	public void initReco(){
		nativeInitCFeatExtractor(nativeCFeatExtractor);
	}

//	public void sayInfo(){
//		nativeSayInfo(nativePerson);
//	}
//	public void writeFile(String pathName, String content){
//		nativeWriteFile(nativePerson, pathName, content);
//	}

	private native long createNativeObject();
	private native void nativeInitCFeatExtractor(long extractorAddr);
//	private native void nativeSayInfo(long personAddr);
//	private native  void nativeWriteFile(long personAddr, String filePathName, String content);
}
