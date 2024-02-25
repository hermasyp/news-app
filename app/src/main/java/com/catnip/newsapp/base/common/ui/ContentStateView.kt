package com.catnip.newsapp.base.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.catnip.newsapp.R
import com.catnip.newsapp.base.exception.NoInternetConnectionException
import com.catnip.newsapp.databinding.LayoutContentStateBinding
import retrofit2.HttpException

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ContentStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        LayoutContentStateBinding.inflate(LayoutInflater.from(context), this, true)

    fun setState(state: ContentState) {
        when (state) {
            ContentState.NORMAL -> {
                this.isVisible = false
                this.binding.groupState.isVisible = false
                this.binding.pbContent.isVisible = false
            }

            ContentState.ERROR_NETWORK -> {
                this.isVisible = true
                this.binding.groupState.isVisible = true
                this.binding.pbContent.isVisible = false
                this.binding.tvState.text = context.getString(R.string.text_error_no_network)
                this.binding.ivState.setImageResource(R.drawable.ic_error_result)
            }

            ContentState.ERROR_COMMON -> {
                this.isVisible = true
                this.binding.groupState.isVisible = true
                this.binding.pbContent.isVisible = false
                this.binding.tvState.text = context.getString(R.string.text_error_common)
                this.binding.ivState.setImageResource(R.drawable.ic_error_result)
            }

            ContentState.EMPTY -> {
                this.isVisible = true
                this.binding.groupState.isVisible = true
                this.binding.pbContent.isVisible = false
                this.binding.tvState.text = context.getString(R.string.text_error_data_empty)
                this.binding.ivState.setImageResource(R.drawable.ic_result_empty)
            }

            ContentState.LOADING -> {
                this.isVisible = true
                this.binding.groupState.isVisible = false
                this.binding.pbContent.isVisible = true
            }
        }
    }
}

fun Exception.toErrorContentState(): ContentState {
    return when(this){
        is NoInternetConnectionException -> ContentState.ERROR_NETWORK
        is HttpException -> ContentState.ERROR_COMMON
        else -> ContentState.ERROR_COMMON
    }
}

enum class ContentState {
    NORMAL,
    LOADING,
    ERROR_NETWORK,
    ERROR_COMMON,
    EMPTY
}