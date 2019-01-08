package com.hujiang.mytest.fragment.aidlFragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.4.1
 * @date 16/1/30
 */
public class AidlFragment extends Fragment {

    @BindView (R.id.tv)
    TextView mTextView;

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
        getActivity().bindService(_Intent,mServiceConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager iBookManager = IBookManager.Stub.asInterface(service);
            try {
                List<Book> bookList = iBookManager.getBookList();
                mTextView.setText(bookList.toString());
                //添加一本书
                Book _Book=new Book(10003,"macBook");
                iBookManager.addBook(_Book);

                bookList=iBookManager.getBookList();
                mTextView.append("\n");
                mTextView.append(bookList.toString());


            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
