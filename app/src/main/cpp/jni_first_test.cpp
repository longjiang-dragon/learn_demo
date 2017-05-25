
#include <android/log.h>


#include <string>
#include <jni.h>
#include "BookManager.h"


using namespace std;

PrintLog *printLog;
BookManager *bookManager;


extern "C" JNIEXPORT jstring JNICALL
Java_com_hujiang_mytest_fragment_JNIFragment_stringFromJNI(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("Hello from JNI");
}


extern "C" JNIEXPORT void JNICALL
Java_com_hujiang_mytest_fragment_JNIFragment_javaToNative(JNIEnv *env, jobject thiz,
                                                          jstring className) {

    const char *utf8 = env->GetStringUTFChars(className, NULL);
    printLog->printMeg(utf8);
}


extern "C" JNIEXPORT void JNICALL
Java_com_hujiang_mytest_fragment_JNIFragment_runTestCode(JNIEnv *env, jobject thiz) {
    bookManager = new BookManager();
    bookManager->start();

}
