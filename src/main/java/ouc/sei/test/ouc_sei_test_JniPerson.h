/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ouc_sei_test_JniPerson */

#ifndef _Included_ouc_sei_test_JniPerson
#define _Included_ouc_sei_test_JniPerson
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ouc_sei_test_JniPerson
 * Method:    createNativeObject
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ouc_sei_test_JniPerson_createNativeObject
  (JNIEnv *, jobject);

/*
 * Class:     ouc_sei_test_JniPerson
 * Method:    nativeInitPerson
 * Signature: (JILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_ouc_sei_test_JniPerson_nativeInitPerson
  (JNIEnv *, jobject, jlong, jint, jstring);

/*
 * Class:     ouc_sei_test_JniPerson
 * Method:    nativeSayInfo
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_ouc_sei_test_JniPerson_nativeSayInfo
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif