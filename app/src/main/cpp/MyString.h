#ifndef MYSTRING_H
#define MYSTRING_H

#include "MyExcept.h"
#include <cstring>  
#include <iostream>  

const int INI_MAX = 0x7fffffff;//2^32npos
const int INI_MIN = 0x80000000;// -2^32  
const int npos = 0xffffffff;// npos  
using namespace std;

class MyString {
public:
    // constructor  
    MyString();//  
    MyString(const MyString &);//  
    MyString(const char *);

    MyString(const size_t, const char);

    // destructor
    ~MyString();

    // attributes
    size_t length();// 字符串长度
    bool isEmpty();// 返回字符串是否为空  
    const char *c_str();// 返回c风格的trr的指针
    // friend funs  
    // read writer operations  
    friend ostream &operator<<(ostream &, const MyString &);

    friend istream &operator>>(istream &, MyString &);

    //add operation
    friend MyString operator+(const MyString &, const MyString &);

    // compare operations
    friend bool operator==(const MyString &, const MyString &);

    friend bool operator!=(const MyString &, const MyString &);

    friend bool operator<(const MyString &, const MyString &);

    friend bool operator<=(const MyString &, const MyString &);

    friend bool operator>(const MyString &, const MyString &);

    friend bool operator>=(const MyString &, const MyString &);

    // 成员函数实现运算符重载,其实一般需要返回自身对象的，成员函数运算符重载会好一些
    // index operation  
    char &operator[](const size_t);

    const char &operator[](const size_t) const;

    // =
    MyString &operator=(const MyString &);

    // +=
    MyString &operator+=(const MyString &);

    // +=
    //MyString operator+=(const MyString&); cannot be overloaded  
    // 成员操作函数  
    // substr  
    MyString substr(size_t pos, const size_t n);

    // append
    MyString &append(const MyString &);

    //insert
    MyString &insert(size_t, const MyString &);

    //assign 替换
    MyString &assign(MyString &, size_t, size_t);

    // erase 删除
    MyString &erase(size_t, size_t);

    //find_first_of 查找某一个字符 size_t 是非符号数的,重载
    // 查找在字符串中第一个与str中的某个字符匹配的字符，返回它的位置。  
    //搜索从index开始，如果没找到就返回string::npos  
    int find_first_of(const char *str, size_t index = 0);

    int find_first_of(const char ch, size_t index = 0);

    int find_first_of(const MyString &, size_t index = 0);

    // 在字符串中查找第一个与str中的字符都不匹配的字符，返回它的位置。搜索从index开始。如果没找到就返回string::nops
    int find_first_not_of(const char *str, size_t index = 0);

    int find_first_not_of(const char ch, size_t index = 0);

    int find_first_not_of(const MyString &, size_t index = 0);

    // swap
    void swap(MyString &lhs, MyString &rhs);

    // replace_all
    MyString &replace_all(const char oldc, const char newc = NULL);

    MyString &replace(size_t index, size_t num1, size_t num2, const char ch);

    //find
    int find(const char *str, size_t index = 0);

    int find(const MyString &str, size_t index = 0);

    int find(const char ch, size_t index = 0);


    //private  
private:
    char *p_str;
    size_t strLength;
};


#endif // MYSTRING_H  