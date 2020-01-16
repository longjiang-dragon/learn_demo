//
// Created by 越风 on 2017/5/24.
//
#include "BookManager.h"
#include <vector>
#include <map>
#include "TemplateDemo.h"
#include "Test2.h"

//#include <vector>
//#include <deque>
//#include <list>
//#include <forward_list>
//#include <array>
//#include <algorithm>


using namespace std;


map<string, Book> bookList;


BookManager::BookManager() {
    myBook1 = new Book("java");
    myBook1->setBookPrice(100);

    myBook2 = new Book("PHP");
    myBook2->setBookPrice(200);

    myBook3 = new Book("C_PLUS");
    myBook3->setBookPrice(300);

    myBook4 = new Book("JS");
    myBook4->setBookPrice(400);

    printLog = new PrintLog();

}

void BookManager::start() {
    testStack();
    myBook1->modificationBookName("head first");

    bookList.insert(pair<string, Book>(myBook1->getBookName(), *myBook1));
    bookList.insert(map<string, Book>::value_type(myBook2->getBookName(), *myBook2));
    bookList.insert(make_pair<string, Book>(myBook3->getBookName(), *myBook3));
    printLog->printMeg(bookList.size());


    for (const auto &des :bookList) {
        printLog->printMeg(des.first);
        printLog->printMeg(des.second.getBookPrice());
    }
    printLog->printMeg(bookList.find("C_PLUS")->second.getBookPrice());//查找一个元素

    printLog->printMeg(bookList.size());
    bookList.erase(bookList.begin());//删除一个item
    printLog->printMeg(bookList.size());


    //迭代器操作
    map<string, Book>::iterator aa = bookList.begin();
    while (aa != bookList.end()) {
        printLog->printMeg(aa->first);
        ++aa;
    }

    auto first_item_add = bookList.begin();//是一个指向pair地址的引用
    printLog->printMeg(first_item_add->first);
    fun2();

}

void BookManager::testStack() {
    Stack<Book, 2> bookStack1;
    bookStack1.push(*myBook1);
    bookStack1.push(*myBook2);
    bookStack1.push(*myBook3);
    printLog->printMeg(bookStack1.top().getBookName());
}