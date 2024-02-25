package com.catnip.newsapp.data.datasource

import com.catnip.newsapp.data.model.news.NewsResponses
import com.catnip.newsapp.data.network.ApiService

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface NewsDataSource {
    suspend fun getNews(): NewsResponses
}

class NewsDataSourceImpl(private val service: ApiService) : NewsDataSource {
    override suspend fun getNews(): NewsResponses {
        return service.getNews()
    }
}