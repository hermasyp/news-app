package com.catnip.newsapp.base.core

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

interface UseCase<T> {
    fun proceed(params: Any? = null): T
}
