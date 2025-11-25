package com.example.myim.ui.conversation.logic

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Stable
import com.example.base.models.Conversation
import com.example.base.models.ServerConnectState
import kotlinx.collections.immutable.PersistentList

@Stable
data class ConversationPageViewState(
    val listState: LazyListState,
    val serverConnectState: ServerConnectState,
    val conversationList: PersistentList<Conversation>,
    val onClickConversation: (Conversation) -> Unit,
    val deleteConversation: (Conversation) -> Unit,
    val pinConversation: (Conversation, Boolean) -> Unit
)