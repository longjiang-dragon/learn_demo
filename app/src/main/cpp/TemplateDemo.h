//
// Created by 越风 on 2017/5/25.
//



#ifndef MYDEMO_STACK_H
#define MYDEMO_STACK_H

#endif //MYDEMO_STACK_H

template<class T, int MAXSIZE>
class Stack {
private:
    T elems[MAXSIZE];
    int numElems;//当前总个数
    PrintLog *printLog;
public:
    Stack();

    void push(T &);

    void pop();

    T top() const;

    bool empty() const {
        return numElems == 0;
    }

    bool full() const {
        return numElems == MAXSIZE;
    }

};

//发现这个方法如果分head和cpp写，会报错
template<class T, int MAXSIZE>
Stack<T, MAXSIZE>::Stack():numElems(0) {
    printLog = new PrintLog();
};

template<class T, int MAXSIZE>
void Stack<T, MAXSIZE>::push(T &elem) {
    if (numElems == MAXSIZE) {
        printLog->printMeg("超过容量了");
        return;
//        throw std::out_of_range("Stack<>::push(): stack is full");
    }
    elems[numElems] = elem;
    numElems++;

}

template<class T, int MAXSIZE>
void Stack<T, MAXSIZE>::pop() {
    if (numElems <= 0) {
        return;
    }
    --numElems;
}

template<class T, int MAXSIZE>
T Stack<T, MAXSIZE>::top() const {
    if (numElems <= 0) {
//        return null;
    }
    return elems[numElems - 1];
}

