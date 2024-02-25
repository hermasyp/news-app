package com.catnip.newsapp.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.catnip.newsapp.data.constants.PreferenceConstants
import com.catnip.newsapp.utils.SharedPreferenceUtils
import com.catnip.newsapp.utils.SharedPreferenceUtils.get
import com.catnip.newsapp.utils.SharedPreferenceUtils.set

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface UserDataSource {
    fun setIsUserLoggedIn(isLoggedIn: Boolean)
    fun isUserLoggedIn(): Boolean
}

class UserDataSourceImpl(private val sharedPreferences: SharedPreferences) : UserDataSource {

    companion object {
        fun createPreference(context: Context) : SharedPreferences{
            return SharedPreferenceUtils.createPreference(context, PreferenceConstants.PREF_NAME)
        }
    }

    override fun setIsUserLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences[PreferenceConstants.PREF_KEY_IS_USER_LOGGED_IN] = isLoggedIn
    }

    override fun isUserLoggedIn(): Boolean {
        return sharedPreferences[PreferenceConstants.PREF_KEY_IS_USER_LOGGED_IN, false]
    }

}