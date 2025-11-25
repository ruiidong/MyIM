package com.example.myim.ui.chat.logic

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Stable
import com.example.base.models.Chat
import com.example.base.models.GroupMemberProfile
import com.example.base.models.GroupProfile
import com.example.base.models.Message
import kotlinx.collections.immutable.PersistentList

@Stable
data class ChatPageViewState(
    val chat: Chat,
    val listState: LazyListState,
    val topBarTitle: String,
    val messageList: PersistentList<Message>
)

@Stable
data class LoadMessageViewState(
    val refreshing: Boolean,
    val loadFinish: Boolean
)

@Stable
data class ChatPageAction(
    val onClickAvatar: (Message) -> Unit,
    val onClickMessage: (Message) -> Unit,
    val onLongClickMessage: (Message) -> Unit
)

@Stable
data class GroupProfilePageViewState(
    val groupProfile: GroupProfile?,
    val memberList: PersistentList<GroupMemberProfile>
)

@Stable
data class GroupProfilePageAction(
    val setAvatar: (String) -> Unit,
    val quitGroup: () -> Unit,
    val onClickMember: (GroupMemberProfile) -> Unit
)