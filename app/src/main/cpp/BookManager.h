//
// Created by 越风 on 2017/5/24.
//


#include "Book.h"
#include "PrintLog.h"

#include <string>


class BookManager {
public:
    BookManager();

    void start();

    void testStack();

private:
    Book *myBook1;
    Book *myBook2;
    Book *myBook3;

    PrintLog *printLog;
};
