package com.catnip.newsapp.base.core

import java.lang.Exception

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
sealed class ResultWrapper<T>(
    val payload: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T? = null) : ResultWrapper<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) : ResultWrapper<T>(data, exception = exception)
    class Empty<T>(data: T? = null) : ResultWrapper<T>(data)
    class Loading<T>(data: T? = null) : ResultWrapper<T>(data)
    class Idle<T>(data: T? = null) : ResultWrapper<T>(data)
}
