package com.example.base.provider

import com.example.base.models.ActionResult
import com.example.base.models.Chat
import com.example.base.models.Conversation
import kotlinx.coroutines.flow.SharedFlow

interface IConversationProvider {

    val conversationList: SharedFlow<List<Conversation>>

    val totalUnreadMessageCount: SharedFlow<Long>

    fun refreshConversationList()

    fun refreshTotalUnreadMessageCount()

    fun cleanConversationUnreadMessageCount(chat: Chat)

    suspend fun pinConversation(conversation: Conversation, pin: Boolean): ActionResult

    suspend fun deleteC2CConversation(userId: String): ActionResult

    suspend fun deleteGroupConversation(groupId: String): ActionResult
}