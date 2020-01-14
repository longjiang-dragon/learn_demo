package com.hujiang.mytest.fragment.mvvm.google.reddit.ui

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.android.example.paging.pagingwithnetwork.reddit.vo.RedditPost
import com.hujiang.mytest.fragment.mvvm.google.reddit.repository.Listing
import com.hujiang.mytest.fragment.mvvm.google.reddit.repository.RedditPostRepository


/**
 * Date:  2020-01-14
 * Time:  17:23
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
class SubRedditViewModel(private val repository: RedditPostRepository) : ViewModel() {
    //这也是一个liveData，也是可以订阅的，这里是给，Transformations.map(subredditName)这里订阅的
    private val subredditName = MutableLiveData<String>()

    private val repoResult = Transformations.map(subredditName, object : Function<String, Listing<RedditPost>> {
        override fun apply(input: String?): Listing<RedditPost> {
            return repository.postsOfSubreddit(input!!, 30);
        }
    });

    val posts = Transformations.switchMap(repoResult, object : Function<Listing<RedditPost>, LiveData<PagedList<RedditPost>>> {
        override fun apply(input: Listing<RedditPost>?): LiveData<PagedList<RedditPost>> {
            return input!!.pagedList;
        }
    })
    val networkState = Transformations.switchMap(repoResult) { it.networkState }
    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }

    fun refresh() {
        //操作符重载，refresh()	refresh.invoke()
        repoResult.value?.refresh?.invoke()
    }

    //搜索内容
    fun showSubreddit(subreddit: String): Boolean {
        if (subredditName.value == subreddit) {
            return false
        }
        subredditName.value = subreddit
        return true
    }

    fun retry() {
        val listing = repoResult?.value
        //相当于        listing!!.retry();
        listing?.retry?.invoke()
    }

    fun currentSubreddit(): String? = subredditName.value

}