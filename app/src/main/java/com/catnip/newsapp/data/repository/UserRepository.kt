package com.catnip.newsapp.data.repository

import com.catnip.newsapp.data.datasource.UserDataSource
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface UserRepository {
    fun setIsUserLoggedIn(isLoggedIn: Boolean)
    fun isUserLoggedIn(): Boolean
}

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {
    override fun setIsUserLoggedIn(isLoggedIn: Boolean) {
        userDataSource.setIsUserLoggedIn(isLoggedIn)
    }

    override fun isUserLoggedIn(): Boolean {
        return userDataSource.isUserLoggedIn()
    }
}