package com.catnip.newsapp.base.core

import com.catnip.newsapp.base.exception.NoInternetConnectionException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun <T> parseException(e: Throwable): ResultWrapper<T> {
    return when (e) {
        is IOException -> ResultWrapper.Error(NoInternetConnectionException())
        is HttpException -> ResultWrapper.Error(e)
        else -> ResultWrapper.Error(e as Exception)
    }
}

fun <T> ResultWrapper<T>.proceedWhen(
    doOnSuccess: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnError: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnLoading: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnEmpty: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnIdle: ((resource: ResultWrapper<T>) -> Unit)? = null,
) {
    when (this) {
        is ResultWrapper.Success -> doOnSuccess?.invoke(this)
        is ResultWrapper.Error -> doOnError?.invoke(this)
        is ResultWrapper.Loading -> doOnLoading?.invoke(this)
        is ResultWrapper.Empty -> doOnEmpty?.invoke(this)
        is ResultWrapper.Idle -> doOnIdle?.invoke(this)
    }
}

fun <T> ResultWrapper<T>.proceedWhen(
    doOnSuccess: ((resource: ResultWrapper<T>) -> Unit),
    doOnError: ((resource: ResultWrapper<T>) -> Unit),
    doOnLoading: ((resource: ResultWrapper<T>) -> Unit)
) = proceedWhen(
    doOnSuccess = doOnSuccess,
    doOnError = doOnError,
    doOnLoading = doOnLoading,
    doOnEmpty = null,
    doOnIdle = null
)
fun <T> proceed(
    providerBlock: suspend () -> T,
): Flow<ResultWrapper<T>> =
    flow<ResultWrapper<T>> {
        val result = providerBlock.invoke()
        emit(
            if (result is Collection<*> && result.isEmpty()) {
                ResultWrapper.Empty(result)
            } else {
                ResultWrapper.Success(result)
            }
        )
    }.catch {
        emit(parseException(it))
    }.onStart {
        emit(ResultWrapper.Loading())
    }

fun <T, R> ResultWrapper<T>.mutate(mapper: (T?) -> R?): ResultWrapper<R> {
    return when (this) {
        is ResultWrapper.Empty -> ResultWrapper.Empty()
        is ResultWrapper.Error -> ResultWrapper.Error(this.exception, mapper.invoke(this.payload))
        is ResultWrapper.Idle -> ResultWrapper.Idle()
        is ResultWrapper.Loading -> ResultWrapper.Loading()
        is ResultWrapper.Success -> ResultWrapper.Success(data = mapper.invoke(this.payload))
    }
}

fun <T, R> Flow<ResultWrapper<T>>.mapMutate(mapper: (T?) -> R?): Flow<ResultWrapper<R>> =
    this.map { it.mutate(mapper) }