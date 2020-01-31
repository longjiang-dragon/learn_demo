// IAddBookResultAidlInterface.aidl
package com.hujiang.mytest.fragment.aidlFragment;


// Declare any non-default types here with import statements

interface IAddBookResultAidlInterface {
            void addBookSuccess(String bookName);
            void addBookFailed(String bookName);
}
