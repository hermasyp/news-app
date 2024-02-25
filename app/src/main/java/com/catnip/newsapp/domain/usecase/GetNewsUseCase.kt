package com.catnip.newsapp.domain.usecase

import com.catnip.newsapp.base.core.ResultWrapper
import com.catnip.newsapp.base.core.UseCase
import com.catnip.newsapp.base.core.mapMutate
import com.catnip.newsapp.data.repository.NewsRepository
import com.catnip.newsapp.domain.mapper.toNewsList
import com.catnip.newsapp.presentation.model.news.News
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class GetNewsUseCase(private val repository: NewsRepository) :
    UseCase<Flow<ResultWrapper<List<News>>>> {
    override fun proceed(params: Any?): Flow<ResultWrapper<List<News>>> {
        return repository.getNews().mapMutate { it?.toNewsList() }
    }
}