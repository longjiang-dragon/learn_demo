package com.hujiang.mytest.fragment.mvvm.google.reddit.ui;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.android.example.paging.pagingwithnetwork.reddit.vo.RedditPost
import com.hujiang.mytest.fragment.aidlFragment.R
import com.hujiang.mytest.fragment.mvvm.google.reddit.GlideApp
import com.hujiang.mytest.fragment.mvvm.google.reddit.ServiceLocator
import com.hujiang.mytest.fragment.mvvm.google.reddit.repository.NetworkState
import com.hujiang.mytest.fragment.mvvm.google.reddit.repository.RedditPostRepository
import kotlinx.android.synthetic.main.activity_mvvm_layout.*


/**
 * Date:  2020-01-14
 * Time:  16:35
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
class MVVMMainActivity : AppCompatActivity() {
    companion object {
        const val KEY_SUBREDDIT = "subreddit"
        const val DEFAULT_SUBREDDIT = "androiddev"
        const val KEY_REPOSITORY_TYPE = "repository_type"

        fun launch(context: Context) {
            val intent = Intent(context, MVVMMainActivity::class.java);
            intent.putExtra(KEY_REPOSITORY_TYPE, RedditPostRepository.Type.IN_MEMORY_BY_ITEM)
            context.startActivity(intent)
        }
    }

    private lateinit var model: SubRedditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_layout)
        model = getViewModel()
        initAdapter()
        initSwipeToRefresh()
        initSearch()
        val subreddit = savedInstanceState?.getString(KEY_SUBREDDIT) ?: DEFAULT_SUBREDDIT
        model.showSubreddit(subreddit)
        supportFragmentManager.beginTransaction().add(R.id.list, TestFragmentViewModelStore()).commit()
    }

    private fun getViewModel(): SubRedditViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repoTypeParam = intent.getIntExtra(KEY_REPOSITORY_TYPE, 0)
                val repoType = RedditPostRepository.Type.values()[repoTypeParam]
                //网络库初始化
                val repo = ServiceLocator.instance(this@MVVMMainActivity)
                        .getRepository(repoType)
                @Suppress("UNCHECKED_CAST")
                return SubRedditViewModel(repo) as T
            }
        }).get(SubRedditViewModel::class.java);
    }

    private fun initAdapter() {
        val glide = GlideApp.with(this)
        val adapter = PostsAdapter(glide) {
            model.retry()
        }
        list.adapter = adapter
        //添加观察者
        model.posts.observe(this, Observer<PagedList<RedditPost>> {
            adapter.submitList(it)
        })
        model.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }

    private fun initSwipeToRefresh() {
        model.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })
        swipe_refresh.setOnRefreshListener {
            model.refresh()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_SUBREDDIT, model.currentSubreddit())
    }

    private fun initSearch() {
        input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updatedSubredditFromInput()
                true
            } else {
                false
            }
        }
        input.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updatedSubredditFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun updatedSubredditFromInput() {
        input.text.trim().toString().let {
            if (it.isNotEmpty()) {
                if (model.showSubreddit(it)) {
                    list.scrollToPosition(0)
                    (list.adapter as? PostsAdapter)?.submitList(null)
                }
            }
        }
    }
}