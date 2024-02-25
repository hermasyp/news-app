package com.catnip.newsapp.data.repository

import com.catnip.newsapp.base.core.ResultWrapper
import com.catnip.newsapp.base.core.proceed
import com.catnip.newsapp.data.datasource.NewsDataSource
import com.catnip.newsapp.data.model.news.NewsItem
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface NewsRepository {
    fun getNews(): Flow<ResultWrapper<List<NewsItem>>>
}

class NewsRepositoryImpl(private val dataSource: NewsDataSource) : NewsRepository {
    override fun getNews(): Flow<ResultWrapper<List<NewsItem>>> {
        return proceed { dataSource.getNews().articles }
    }
}