package com.example.myim.ui.friend.logic

import androidx.compose.runtime.Stable
import com.example.base.models.PersonProfile

@Stable
data class FriendProfilePageViewState(
    val personProfile: PersonProfile?,
    val itIsMe: Boolean,
    val isFriend: Boolean,
    val showSetFriendRemarkPanel: () -> Unit,
    val addFriend: () -> Unit
)

@Stable
data class SetFriendRemarkDialogViewState(
    val visible: Boolean,
    val remark: String,
    val setFriendRemark: (String) -> Unit,
    val dismissDialog: () -> Unit
)