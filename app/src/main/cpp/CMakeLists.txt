cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -UNDEBUG")

add_library(jni_first_test SHARED
           jni_first_test.cpp
           PrintLog.cpp
           Book.cpp
           BookManager.cpp
           TemplateDemo.h
           Test1.cpp
           Test2.cpp)

# Include libraries needed for hello-jni lib
target_link_libraries(jni_first_test
                      android
                      log)


