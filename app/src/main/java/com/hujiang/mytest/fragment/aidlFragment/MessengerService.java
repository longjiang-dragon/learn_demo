package com.hujiang.mytest.fragment.aidlFragment;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.Nullable;

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

        private RemoteCallbackList<IAddBookResultAidlInterface> callbackS = new RemoteCallbackList<>();

        @Override
        public void addBook(Book book) throws RemoteException {
            if (null == book) return;
            if (!mBookList.contains(book)) {
                mBookList.add(book);
                notifyCallback(true, book.getBookName());
            } else {
                notifyCallback(false, book.getBookName());
            }
        }

        private void notifyCallback(boolean isAddSuccess, String bookName) throws RemoteException {
            int count = callbackS.beginBroadcast();
            for (int i = 0; i < count; i++) {
                if (isAddSuccess) {
                    callbackS.getBroadcastItem(i).addBookSuccess(bookName);
                } else {
                    callbackS.getBroadcastItem(i).addBookFailed(bookName);
                }

            }
            //成对使用
            callbackS.finishBroadcast();
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void registerListener(IAddBookResultAidlInterface callBack) throws RemoteException {
            callbackS.register(callBack);
        }

        @Override
        public void unregisterListener(IAddBookResultAidlInterface callBack) throws RemoteException {
            if (null != callBack) {
                callbackS.unregister(callBack);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
