#ifndef MYEXCEPT_H
#define MYEXCEPT_H
#include "MyExcept.h"
#include<cstring>
#include<iostream>
using namespace std;

class Myexcep
{
    public:
        Myexcep() :m_exnote("Get a exception ") {}
        Myexcep(const char *other)
        {
            if(NULL == other)
            {
                m_exnote = new char[1];
                m_exnote = '\0';
                return;
            }
            m_exnote = new char[strlen(other)+1];
            strncpy(m_exnote,other,strlen(other)+1);
        }
        virtual ~Myexcep(){ delete[] m_exnote;}
        virtual void show_message() {cout << m_exnote <<endl;}
    private:
        char *m_exnote;
};

class Outofbond :public Myexcep
{
    public:
        Outofbond() :m_outnote("Get a Out of bond exception ") {}
        Outofbond(const char *other)
        {
            if(NULL == other)
            {
                m_outnote = new char[1];
                m_outnote = '\0';
                return;
            }
            m_outnote = new char[strlen(other)+1];
            strncpy(m_outnote,other,strlen(other)+1);
        }
        ~Outofbond(){ delete[] m_outnote;}
        void show_message(){cout << m_outnote <<endl;}
    private:
        char* m_outnote;
};

class Allocfail :public Myexcep
{
    public:
        Allocfail():m_alonote("Get a Allocate fail exception "){}
        Allocfail(const char* &other)
        {
            if(NULL == other)
            {
                m_alonote = new char[1];
                m_alonote = '\0';
                return;
            }
            m_alonote = new char[strlen(other)+1];
            strncpy(m_alonote,other,strlen(other)+1);
        }
        ~Allocfail(){}
        void show_message(){cout << m_alonote <<endl;}
    private:
        char* m_alonote;
};



#endif // MYEXCEPT_H
