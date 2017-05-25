//
// Created by 越风 on 2017/5/24.
//
#include "Book.h"

Book::Book() {
    Book("default book");
}

Book::Book(string bookName) {
    mBookName = bookName;
}

string Book::getBookName() {
    return mBookName;
}

void Book::modificationBookName(string newBookName) {
    mBookName = newBookName;
}

int Book::getBookPrice() const {
    return mBookPrice;
}

void Book::setBookPrice(int bookPrice) {
    mBookPrice = bookPrice;
}



