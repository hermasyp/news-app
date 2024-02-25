package com.catnip.newsapp.presentation.feature.splash

import androidx.lifecycle.ViewModel
import com.catnip.newsapp.domain.usecase.CheckUserLoginUseCase

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class SplashViewModel(private val checkUserLoginUseCase: CheckUserLoginUseCase) : ViewModel() {
    fun isUserLogin() = checkUserLoginUseCase.proceed()
}