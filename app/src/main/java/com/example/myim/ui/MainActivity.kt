package com.example.myim.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.example.myim.ui.base.BaseActivity
import com.example.myim.ui.logic.MainViewModel
import com.example.myim.ui.person.logic.PersonProfileViewModel
import kotlin.getValue

class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val personProfileViewModel by viewModels<PersonProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPage(
                mainViewModel = mainViewModel,
                personProfileViewModel = personProfileViewModel
            )
        }
    }
}