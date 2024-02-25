package com.catnip.newsapp.domain.usecase

import com.catnip.newsapp.base.core.UseCase
import com.catnip.newsapp.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LogoutUseCase(private val userRepository: UserRepository) : UseCase<Flow<Boolean>> {
    override fun proceed(params: Any?): Flow<Boolean> {
        return flow {
            try {
                userRepository.setIsUserLoggedIn(false)
                emit(true)
            } catch (e: Exception) {
                emit(false)
            }
        }
    }
}