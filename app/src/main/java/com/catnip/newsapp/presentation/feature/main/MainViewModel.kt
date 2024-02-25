package com.catnip.newsapp.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.newsapp.base.core.ResultWrapper
import com.catnip.newsapp.domain.usecase.GetNewsUseCase
import com.catnip.newsapp.domain.usecase.LogoutUseCase
import com.catnip.newsapp.presentation.model.news.News
import kotlinx.coroutines.Dispatchers

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val newsResult: LiveData<ResultWrapper<List<News>>>
        get() = getNewsUseCase.proceed().asLiveData(Dispatchers.IO)

    fun doLogout() = logoutUseCase.proceed().asLiveData(Dispatchers.IO)

}