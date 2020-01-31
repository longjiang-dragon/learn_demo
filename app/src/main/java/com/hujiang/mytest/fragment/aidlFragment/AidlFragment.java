package com.hujiang.mytest.fragment.aidlFragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yuefeng
 * @version 3.4.1
 * @date 16/1/30
 * 1、实现与service单向通信：通过bindService方法，实现与serivce的单向通信
 * 2、实现service向activity单向通信:添加aidl回调实现
 *
 */
public class AidlFragment extends Fragment {

    @BindView (R.id.tv)
    TextView mTextView;
    private IBookManager iBookManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _View = inflater.inflate(R.layout.aidl_fragment_layout, container, false);
        ButterKnife.bind(this, _View);
        return _View;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent _Intent = new Intent(getActivity(), MessengerService.class);
        getActivity().bindService(_Intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @OnClick ({R.id.add_book, R.id.add_add_callback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_add_callback:
                addCallbackAction();
                break;
            case R.id.add_book:
                addBookAction();
                break;
        }

    }

    private void addCallbackAction() {
        try {
            iBookManager.registerListener(new IAddBookResultAidlInterface.Stub() {
                @Override
                public void addBookSuccess(String bookName) throws RemoteException {
                    ToastUtils.showShort("添加 " + bookName + "  成功");

                }

                @Override
                public void addBookFailed(String bookName) throws RemoteException {
                    ToastUtils.showShort("添加 " + bookName + "  失败");
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addBookAction() {
        try {
            //添加一本书
            Book _Book = new Book(10003, "macBook");
            iBookManager.addBook(_Book);
            List<Book> bookList = iBookManager.getBookList();
            mTextView.setText(bookList.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
