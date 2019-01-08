package com.hujiang.mytest.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hujiang.mytest.fragment.aidlFragment.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 16/3/27
 */
public class AfterFollowFragment extends Fragment {
	private static int mScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

	@BindView (R.id.recycler_view)
	public RecyclerView mRecyclerView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View _view = inflater.inflate(R.layout.after_follew_layout, container, false);
		ButterKnife.bind(this, _view);
		return _view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		this.mRecyclerView.setAdapter(new AfterFollowAdapter());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public class AfterFollowAdapter extends RecyclerView.Adapter<AfterFollowAdapter.Item> {
		private List<ItemData> mDataList;

		String _textValue = "哈哈哈哈俣哈哈kwgi";
		String _buttonValue = "button";

		public AfterFollowAdapter() {
			mDataList = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				ItemData _itemData = new ItemData();
				for (int j = 0; j < i; j++) {
					_itemData.setpTextViewText(_textValue);
					_itemData.setpButtonText(_buttonValue);

				}
				mDataList.add(_itemData);
			}
		}

		@Override
		public Item onCreateViewHolder(ViewGroup parent, int viewType) {
			View _view = LayoutInflater.from(getActivity()).inflate(R.layout.after_follew_item, parent, false);
			return new Item(_view);
		}

		@Override
		public void onBindViewHolder(Item holder, int position) {
			holder.BindData(mDataList.get(position));
		}


		@Override
		public int getItemCount() {
			return mDataList.size();
		}

		public class Item extends RecyclerView.ViewHolder {
			private TextView mTextView;
			private Button mButton;

			public void BindData(ItemData pItemData) {
				this.mButton.setText(pItemData.pButtonText);
				this.mButton.post(new Runnable() {
					@Override
					public void run() {
						mTextView.getLayoutParams().width=mScreenWidth - mButton.getMeasuredWidth();
						mTextView.requestLayout();
					}
				});
				this.mTextView.setText(pItemData.getpTextViewText());

			}

			public Item(View itemView) {
				super(itemView);
				this.mButton = (Button) itemView.findViewById(R.id.button);
				this.mTextView = (TextView) itemView.findViewById(R.id.tv);
			}
		}

		public class ItemData {
			private String pButtonText;
			private String pTextViewText;

			public String getpButtonText() {
				return pButtonText;
			}

			public void setpButtonText(String pPButtonText) {
				pButtonText += pPButtonText;
			}

			public String getpTextViewText() {
				return pTextViewText;
			}

			public void setpTextViewText(String pPTextViewText) {
				pTextViewText += pPTextViewText;
			}
		}
	}
}
