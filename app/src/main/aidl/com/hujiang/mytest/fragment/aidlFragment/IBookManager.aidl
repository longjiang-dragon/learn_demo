// IBookManager.aidl
package com.hujiang.mytest.fragment.aidlFragment;
import com.hujiang.mytest.fragment.aidlFragment.Book;
import com.hujiang.mytest.fragment.aidlFragment.IAddBookResultAidlInterface;



// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void addBook(in Book book);
   List<Book> getBookList();
   void registerListener(IAddBookResultAidlInterface callBack);
   void unregisterListener(IAddBookResultAidlInterface callBack);

}
