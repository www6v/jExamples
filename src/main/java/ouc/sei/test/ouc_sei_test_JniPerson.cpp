
#include"ouc_sei_test_JniPerson.h"
#include "person.h"

JNIEXPORT jlong JNICALL Java_ouc_sei_test_JniPerson_createNativeObject
(JNIEnv * env, jobject obj){
	jlong result;
	result =(jlong) new Person();
	return result;
}

/*
 * Class:     ouc_sei_test_JniPerson
 * Method:    nativeInitPerson
 * Signature: (JILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_ouc_sei_test_JniPerson_nativeInitPerson
  (JNIEnv * env, jobject obj, jlong thiz, jint jage, jstring jname){
	const char* name_str = env->GetStringUTFChars(jname,0);
	((Person*)thiz)->init(jage,name_str);
}

/*
 * Class:     ouc_sei_test_JniPerson
 * Method:    nativeSayInfo
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_ouc_sei_test_JniPerson_nativeSayInfo
  (JNIEnv * env, jobject obj, jlong thiz){
	((Person*)thiz)->say_info();
}

JNIEXPORT void JNICALL Java_ouc_sei_test_JniPerson_nativeWriteFile
(JNIEnv *env, jobject jclass,jlong thiz, jstring file_path_name, jstring text_content){
	const char* name;
	const char* filecontent;
	name=env->GetStringUTFChars(file_path_name,0);
	filecontent=env->GetStringUTFChars(text_content,0);
	((Person*)thiz)->writeFile(name,filecontent);
}



