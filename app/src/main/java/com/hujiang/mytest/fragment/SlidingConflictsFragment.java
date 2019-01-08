package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 16/3/25
 */
public class SlidingConflictsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	@BindView (R.id.recycler_view)
	public RecyclerView mRecyclerView;
	@BindView(R.id.swipe_refresh_layout)
	public SwipeRefreshLayout mSwipeRefreshLayout;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sliding_conflicts_layout, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ButterKnife.bind(this, getView());
		initUI();
	}

	private void initUI() {
		this.mSwipeRefreshLayout.setOnRefreshListener(this);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setAdapter(new MyAdapter());
		mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				int topRowVerticalPosition =
						(recyclerView == null || recyclerView.getChildCount() == 0) ?
								0 : recyclerView.getChildAt(0).getTop();
				Log.i("info",recyclerView.getChildAt(0).getTop()+"'");
				mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);

			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
			}
		});
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(false);
			}
		},4000);
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
				mTextView.setText("滑动冲突测试item====" + pPosition);

			}

			public ItemHolder(View itemView) {
				super(itemView);
				mTextView = (TextView) itemView.findViewById(R.id.textview);
			}
		}
	}

}
