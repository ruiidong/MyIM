package com.example.myim.ui.conversation.logic

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.base.models.ActionResult
import com.example.base.models.Chat
import com.example.base.models.Conversation
import com.example.base.models.ConversationType
import com.example.base.models.ServerConnectState
import com.example.base.provider.IAccountProvider
import com.example.base.provider.IConversationProvider
import com.example.myim.ui.base.BaseViewModel
import com.example.myim.ui.chat.ChatActivity
import com.example.myim.ui.logic.MyIM
import com.example.proxy.ConversationProvider
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

class ConversationViewModel : BaseViewModel() {

    private val conversationProvider: IConversationProvider = ConversationProvider()

    private val accountProvider: IAccountProvider = MyIM.accountProvider

    var pageViewState by mutableStateOf(
        value = ConversationPageViewState(
            listState = LazyListState(
                firstVisibleItemIndex = 0,
                firstVisibleItemScrollOffset = 0
            ),
            serverConnectState = ServerConnectState.Idle,
            conversationList = persistentListOf(),
            onClickConversation = ::onClickConversation,
            deleteConversation = ::deleteConversation,
            pinConversation = ::pinConversation
        )
    )
        private set

    init {
        viewModelScope.launch {
            launch {
                conversationProvider.conversationList.collect {
                    pageViewState = pageViewState.copy(conversationList = it.toPersistentList())
                }
            }
            launch {
                accountProvider.serverConnectState.collect {
                    pageViewState = pageViewState.copy(serverConnectState = it)
                }
            }
        }
        conversationProvider.refreshConversationList()
    }

    private fun onClickConversation(conversation: Conversation) {
        when (conversation.type) {
            ConversationType.C2C -> {
                ChatActivity.navTo(
                    context = context,
                    chat = Chat.PrivateChat(id = conversation.id)
                )
            }

            ConversationType.Group -> {
                ChatActivity.navTo(
                    context = context,
                    chat = Chat.GroupChat(id = conversation.id)
                )
            }
        }
    }

    private fun deleteConversation(conversation: Conversation) {
        viewModelScope.launch {
            val result = when (conversation.type) {
                ConversationType.C2C -> {
                    conversationProvider.deleteC2CConversation(userId = conversation.id)
                }

                ConversationType.Group -> {
                    conversationProvider.deleteGroupConversation(groupId = conversation.id)
                }
            }
            when (result) {
                is ActionResult.Success -> {
                    conversationProvider.refreshConversationList()
                }

                is ActionResult.Failed -> {
                    showToast(msg = result.reason)
                }
            }
        }
    }

    private fun pinConversation(conversation: Conversation, pin: Boolean) {
        viewModelScope.launch {
            conversationProvider.pinConversation(
                conversation = conversation,
                pin = pin
            )
        }
    }

}