package com.hujiang.mytest.fragment.mvvm.google.reddit.repository

import com.android.example.paging.pagingwithnetwork.reddit.vo.RedditPost


/**
 * Date:  2020-01-14
 * Time:  17:24
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
interface RedditPostRepository {
    fun postsOfSubreddit(subReddit: String, pageSize: Int): Listing<RedditPost>

    enum class Type {
        IN_MEMORY_BY_ITEM,
        IN_MEMORY_BY_PAGE,
        DB
    }
}