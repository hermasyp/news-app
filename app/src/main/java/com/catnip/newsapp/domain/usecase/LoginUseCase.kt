package com.catnip.newsapp.domain.usecase

import com.catnip.newsapp.base.core.UseCase
import com.catnip.newsapp.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LoginUseCase(private val userRepository: UserRepository) : UseCase<Flow<Boolean>> {
    override fun proceed(params: Any?): Flow<Boolean> {
        if (params !is LoginParams) throw IllegalStateException("Wrong Params")
        return flow {
            if (params.username == "magnus" && params.password == "magnusaja") {
                userRepository.setIsUserLoggedIn(true)
                emit(true)
            } else {
                emit(false)
            }
        }
    }

    data class LoginParams(val username: String, val password: String)
}
