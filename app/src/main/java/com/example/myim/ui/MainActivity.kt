package com.example.myim.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myim.ui.base.BaseActivity
import com.example.myim.ui.friendship.logic.FriendshipViewModel
import com.example.myim.ui.logic.MainViewModel
import com.example.myim.ui.login.LoginActivity
import com.example.myim.ui.person.logic.PersonProfileViewModel
import kotlinx.coroutines.launch
import kotlin.getValue

class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    private val friendshipViewModel by viewModels<FriendshipViewModel>()

    private val personProfileViewModel by viewModels<PersonProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPage(
                mainViewModel = mainViewModel,
                friendshipViewModel = friendshipViewModel,
                personProfileViewModel = personProfileViewModel
            )
        }
    }
}