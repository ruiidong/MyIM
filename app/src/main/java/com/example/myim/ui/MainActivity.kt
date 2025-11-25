package com.example.myim.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.base.models.ServerConnectState
import com.example.myim.provider.AccountProvider
import com.example.myim.ui.base.BaseActivity
import com.example.myim.ui.conversation.logic.ConversationViewModel
import com.example.myim.ui.friendship.logic.FriendshipViewModel
import com.example.myim.ui.logic.MainViewModel
import com.example.myim.ui.login.LoginActivity
import com.example.myim.ui.person.logic.PersonProfileViewModel
import kotlinx.coroutines.launch
import kotlin.getValue

class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    private val conversationViewModel by viewModels<ConversationViewModel>()

    private val friendshipViewModel by viewModels<FriendshipViewModel>()

    private val personProfileViewModel by viewModels<PersonProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPage(
                mainViewModel = mainViewModel,
                conversationViewModel = conversationViewModel,
                friendshipViewModel = friendshipViewModel,
                personProfileViewModel = personProfileViewModel
            )
        }
        initEvent()
    }

    private fun initEvent() {
        lifecycleScope.launch {
            mainViewModel.serverConnectState.collect {
                when (it) {
                    ServerConnectState.KickedOffline -> {
                        showToast(msg = "本账号已在其它客户端登陆，请重新登陆")
                        AccountProvider.onUserLogout()
                        navToLoginPage()
                    }

                    ServerConnectState.Logout, ServerConnectState.UserSigExpired -> {
                        navToLoginPage()
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun navToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}