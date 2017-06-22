//
// Created by 越风 on 2017/6/2.
//

#ifndef MYDEMO_STRINGTEST_H
#define MYDEMO_STRINGTEST_H

#include <string>

using namespace std;

class StringTest {
    string s1;
    string s2;
    string s3;

    void plus() {
        s3 = s1 + s2;
        //        s3="a"+"b";  必需要有一个是string对象。这里两个都是字面值.不能把字面值直接相加
        s3 = "" + s2;
        s3 = s1 + "" + string("aa");
    }

};

#endif MYDEMO_STRINGTEST_H
