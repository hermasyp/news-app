package com.catnip.newsapp.domain.usecase

import com.catnip.newsapp.base.core.UseCase
import com.catnip.newsapp.data.repository.UserRepository

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CheckUserLoginUseCase(private val userRepository: UserRepository) : UseCase<Boolean> {
    override fun proceed(params: Any?): Boolean {
        return userRepository.isUserLoggedIn()
    }
}