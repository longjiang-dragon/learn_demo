//
// Created by 越风 on 2017/6/26.
//
#include "Test2.h"
#include <iostream>
#include "PrintLog.h"

PrintLog printLogTest;

void fun2() {
    printLogTest.printMeg(g_str);
    printLogTest.printMeg(g_str_static);
}

