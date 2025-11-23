package com.example.myim

import android.app.Application
import com.example.myim.provider.AccountProvider
import com.example.myim.provider.AppThemeProvider
import com.example.myim.provider.ContextProvider
import com.example.myim.ui.logic.MyIM

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ContextProvider.init(application = this)
        AppThemeProvider.init(application = this)
        AccountProvider.init(application = this)
        MyIM.accountProvider.init(application = this)
    }
}