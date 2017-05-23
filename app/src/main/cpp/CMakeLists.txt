cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -UNDEBUG")

add_library(jni_frist_test SHARED
           jni_frist_test.cpp)

# Include libraries needed for hello-jni lib
target_link_libraries(jni_frist_test
                      android
                      log)


