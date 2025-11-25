package com.example.myim.ui.chat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.IntentCompat
import com.example.base.models.Chat
import com.example.base.models.ImageMessage
import com.example.base.models.SystemMessage
import com.example.base.models.TextMessage
import com.example.base.models.TimeMessage
import com.example.myim.ui.base.BaseActivity
import com.example.myim.ui.chat.logic.ChatPageAction
import com.example.myim.ui.chat.logic.ChatViewModel
import com.example.myim.ui.friend.FriendProfileActivity
import com.example.myim.ui.preview.PreviewImageActivity
import com.example.myim.ui.theme.WindowInsetsEmpty

class ChatActivity : BaseActivity() {

    companion object {

        private const val KEY_CHAT = "keyChat"

        fun navTo(context: Context, chat: Chat) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(KEY_CHAT, chat)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }

    }

    private val chat by lazy(mode = LazyThreadSafetyMode.NONE) {
        IntentCompat.getParcelableExtra(intent,
            KEY_CHAT, Chat::class.java)!!
    }

    private val chatViewModel by viewModelsInstance {
        ChatViewModel(chat = chat)
    }

    private val chatPageAction = ChatPageAction(
        onClickAvatar = {
            val messageSenderId = it.detail.sender.id
            if (messageSenderId.isNotBlank()) {
                FriendProfileActivity.navTo(context = this, friendId = messageSenderId)
            }
        },
        onClickMessage = {
            when (it) {
                is ImageMessage -> {
                    val allImageUrl = chatViewModel.filterAllImageMessageUrl()
                    val initialImageUrl = it.previewImageUrl
                    if (allImageUrl.isNotEmpty() && initialImageUrl.isNotBlank()) {
                        val initialPage = allImageUrl.indexOf(element = initialImageUrl)
                            .coerceAtLeast(minimumValue = 0)
                        PreviewImageActivity.navTo(
                            context = this,
                            imageUriList = allImageUrl,
                            initialPage = initialPage
                        )
                    }
                }

                is TextMessage, is SystemMessage, is TimeMessage -> {

                }
            }
        },
        onLongClickMessage = {

        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatPage(
                chatViewModel = chatViewModel,
                chatPageAction = chatPageAction
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatPage(chatViewModel: ChatViewModel, chatPageAction: ChatPageAction) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        contentWindowInsets = WindowInsetsEmpty,
        topBar = {
            ChatPageTopBar(
                title = chatViewModel.chatPageViewState.topBarTitle,
                chat = chatViewModel.chatPageViewState.chat
            )
        },
        bottomBar = {
            ChatPageBottomBar(chatViewModel = chatViewModel)
        }
    ) { innerPadding ->
        val pullRefreshState = rememberPullRefreshState(
            refreshing = chatViewModel.loadMessageViewState.refreshing,
            onRefresh = {
                chatViewModel.loadMoreMessage()
            }
        )
        Box(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize()
                .pullRefresh(
                    state = pullRefreshState,
                    enabled = !chatViewModel.loadMessageViewState.loadFinish
                )
        ) {
            MessagePanel(
                pageViewState = chatViewModel.chatPageViewState,
                chatPageAction = chatPageAction
            )
            PullRefreshIndicator(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter),
                refreshing = chatViewModel.loadMessageViewState.refreshing,
                state = pullRefreshState,
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF42A5F5)
            )
        }
    }
}