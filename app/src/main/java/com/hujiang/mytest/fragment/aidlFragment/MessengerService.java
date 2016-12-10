package com.hujiang.mytest.fragment.aidlFragment;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yuefeng
 * @version 3.4.1
 * @date 16/1/30
 */
public class MessengerService extends Service {
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(11112, "Head First 设计模式"));
        mBookList.add(new Book(11113, "Android 开发艺术探索"));
    }

    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
