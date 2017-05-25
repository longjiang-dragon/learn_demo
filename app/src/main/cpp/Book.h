//
// Created by 越风 on 2017/5/24.
//
#include <stdio.h>
#include <string>

using namespace std;

class Book {
public:
    Book();

    Book(string bookName);

    void modificationBookName(string newBookName);

    string getBookName();

    int getBookPrice() const ;

    void setBookPrice(int bookPrice);

private:
    string mBookName;
    int mBookPrice;
    int mBookPageCount;
};