//
// Created by 越风 on 2017/5/24.
//

#ifndef MYDEMO_TEST_H
#define MYDEMO_TEST_H

#endif //MYDEMO_TEST_H

#include <string>


class PrintLog {
public:
    void printMeg(std::string msgContent);

    void printMeg(int msg);

private:
    std::string to_string(int _Val);
};