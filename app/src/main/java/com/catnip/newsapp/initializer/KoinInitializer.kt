package com.catnip.newsapp.initializer

import android.content.Context
import androidx.startup.Initializer
import com.catnip.newsapp.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class KoinInitializer : Initializer<KoinApplication>{
    override fun create(context: Context): KoinApplication {
       return startKoin {
           androidLogger()
           androidContext(context)
           modules(AppModules.modules)
       }
    }
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}