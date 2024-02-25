package com.catnip.newsapp.di

import android.service.autofill.UserData
import com.catnip.newsapp.data.datasource.NewsDataSource
import com.catnip.newsapp.data.datasource.NewsDataSourceImpl
import com.catnip.newsapp.data.datasource.UserDataSource
import com.catnip.newsapp.data.datasource.UserDataSourceImpl
import com.catnip.newsapp.data.network.ApiService
import com.catnip.newsapp.data.repository.NewsRepository
import com.catnip.newsapp.data.repository.NewsRepositoryImpl
import com.catnip.newsapp.data.repository.UserRepository
import com.catnip.newsapp.data.repository.UserRepositoryImpl
import com.catnip.newsapp.domain.usecase.GetNewsUseCase
import com.catnip.newsapp.domain.usecase.LoginUseCase
import com.chuckerteam.chucker.api.ChuckerInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object AppModules {

    private val networkModule = module {
        single { ChuckerInterceptor(androidContext()) }
        single { GsonConverterFactory.create() }
        single { ApiService.invoke(get(), get()) }
    }

    private val localModule = module {
        single { UserDataSourceImpl.createPreference(androidContext()) }
    }

    private val dataSourceModule = module {
        single<NewsDataSource> { NewsDataSourceImpl(get()) }
        single<UserDataSource> { UserDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<NewsRepository> { NewsRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
    }

    private val useCaseModule = module {
        single { GetNewsUseCase(get()) }
        single { LoginUseCase(get()) }
    }

    private val viewModelModule = module {

    }

    val modules: List<Module> = listOf(
        networkModule,
        localModule,
        dataSourceModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
    )
}
