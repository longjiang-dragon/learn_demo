package com.hujiang.mytest.fragment.fixHeigth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hujiang.mytest.fragment.aidlFragment.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 16/3/25
 */
public class FixHeightRecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.recycler_view)
    public RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_fix_heigth_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());
        initUI();
    }

    private void initUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MyAdapter());
        mRecyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemHolder> {
        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View _view = getActivity().getLayoutInflater().inflate(R.layout.list_item, parent, false);
            return new ItemHolder(_view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.dataBind(position);
        }


        @Override
        public int getItemCount() {
            return 17;
        }

        public class ItemHolder extends RecyclerView.ViewHolder {
            private TextView mTextView;

            public void dataBind(int pPosition) {
                mTextView.append("滑动冲突测试item====" + pPosition);
                mTextView.append("滑动冲突测试item====" + pPosition);


            }

            public ItemHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.textview);
            }
        }
    }

}
