package com.catnip.newsapp.data.model.news

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class NewsItem(
    @SerializedName("author")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
)
