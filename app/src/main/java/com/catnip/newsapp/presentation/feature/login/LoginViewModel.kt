package com.catnip.newsapp.presentation.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.newsapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun doLogin(username: String, password: String) =
        loginUseCase.proceed(LoginUseCase.LoginParams(username.trim(), password.trim()))
            .asLiveData(Dispatchers.IO)
}