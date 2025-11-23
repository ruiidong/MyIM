package com.example.myim.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myim.provider.AppThemeProvider
import com.example.myim.ui.logic.AppTheme
import com.example.myim.ui.theme.MyIMTheme

open class BaseActivity : AppCompatActivity() {

    protected inline fun <reified T : ViewModel> viewModelsInstance(crossinline create: () -> T): Lazy<T> {
        return viewModels(factoryProducer = {
            object : ViewModelProvider.NewInstanceFactory(), ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return create() as T
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
    }

    protected fun setContent(
        systemBarUi: @Composable () -> Unit = {
            SetSystemBarUi()
        },
        content: @Composable () -> Unit
    ) {
        setContent(
            parent = null,
            content = {
                MyIMTheme {
                    systemBarUi()
                    content()
                }
            }
        )
    }

    @Composable
    protected open fun SetSystemBarUi() {
        SetSystemBarUi(appTheme = AppThemeProvider.appTheme)
    }

    @Composable
    private fun SetSystemBarUi(appTheme: AppTheme) {
        val context = LocalContext.current
        LaunchedEffect(key1 = appTheme == AppTheme.Dark) {
            if (context is Activity) {
                val systemBarsDarkIcon = when (appTheme) {
                    AppTheme.Light, AppTheme.Gray -> {
                        true
                    }

                    AppTheme.Dark -> {
                        false
                    }
                }
                val window = context.window
                WindowInsetsControllerCompat(window, window.decorView).apply {
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                    isAppearanceLightStatusBars = systemBarsDarkIcon
                    isAppearanceLightNavigationBars = systemBarsDarkIcon
                }
            }
        }
    }

    protected inline fun <reified T : Activity> startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}