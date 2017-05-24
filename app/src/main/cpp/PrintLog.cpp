//
// Created by 越风 on 2017/5/24.
//
#define TAG "native_log"
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)


#include "PrintLog.h"
#include <android/log.h>
#include <iostream>


//fprintf  用于打印内容到文件
/**
  * String 转 char*
  *     msgContent.c_str();
  *     msgContent.data()
  *     关于String与char互转  的ref:   https://www.cnblogs.com/Pillar/p/4206452.html
  */
void PrintLog::printMeg(string msgContent) {
    LOGV("%s", msgContent.data());
    cout << msgContent << endl;
}