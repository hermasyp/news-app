package com.catnip.newsapp.domain.mapper

import com.catnip.newsapp.data.model.news.NewsItem
import com.catnip.newsapp.presentation.model.news.News

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun NewsItem.toNews(): News = News(
    author = this.author.orEmpty(),
    title = this.title.orEmpty(),
    url = this.url.orEmpty(),
    publishedAt = this.publishedAt.orEmpty()
)

fun Collection<NewsItem>.toNewsList(): List<News> = this.map { it.toNews() }