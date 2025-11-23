package com.example.myim.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.myim.provider.ContextProvider
import com.example.myim.provider.ToastProvider

open class BaseViewModel : ViewModel() {

    protected val context: Application
        get() = ContextProvider.context

    protected fun showToast(msg: String) {
        ToastProvider.showToast(context = context, msg = msg)
    }
}