package com.example.myim.ui.friendship.logic

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.base.models.ActionResult
import com.example.base.models.Chat
import com.example.base.models.GroupProfile
import com.example.base.models.PersonProfile
import com.example.base.provider.IFriendshipProvider
import com.example.base.provider.IGroupProvider
import com.example.myim.ui.base.BaseViewModel
import com.example.myim.ui.chat.ChatActivity
import com.example.myim.ui.friend.FriendProfileActivity
import com.example.proxy.FriendshipProvider
import com.example.proxy.GroupProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList

class FriendshipViewModel : BaseViewModel() {

    private val friendshipProvider: IFriendshipProvider = FriendshipProvider()

    private val groupProvider: IGroupProvider = GroupProvider()

    var pageViewState by mutableStateOf(
        value = FriendshipPageViewState(
            listState = LazyListState(
                firstVisibleItemIndex = 0,
                firstVisibleItemScrollOffset = 0
            ),
            joinedGroupList = persistentListOf(),
            friendList = persistentListOf(),
            onClickGroupItem = ::onClickGroupItem,
            onClickFriendItem = ::onClickFriendItem,
            showFriendshipDialog = ::showFriendshipDialog
        )
    )
        private set

    var friendshipDialogViewState by mutableStateOf(
        value = FriendshipDialogViewState(
            visible = false,
            groupIds = persistentListOf(),
            dismissDialog = ::dismissFriendshipDialog,
            joinGroup = ::joinGroup,
            addFriend = ::addFriend
        )
    )
        private set

    init {
        viewModelScope.launch {
            launch {
                groupProvider.joinedGroupList.collect {
                    pageViewState = pageViewState.copy(joinedGroupList = it.toPersistentList())
                }
            }
            launch {
                friendshipProvider.friendList.collect {
                    pageViewState = pageViewState.copy(friendList = it.toPersistentList())
                }
            }
        }
        groupProvider.refreshJoinedGroupList()
        friendshipProvider.refreshFriendList()
    }

    private fun onClickGroupItem(groupProfile: GroupProfile) {
        ChatActivity.navTo(
            context = context,
            chat = Chat.GroupChat(id = groupProfile.id)
        )
    }

    private fun onClickFriendItem(personProfile: PersonProfile) {
        FriendProfileActivity.navTo(
            context = context,
            friendId = personProfile.id
        )
    }

    fun showFriendshipDialog() {
        val ids = listOf(
            "@TGS#3ITGDDHSI",
            "@TGS#3G5JDDHSJ",
            "@TGS#333KDDHST"
        )
        val groupIds = ids.mapIndexed { index, id ->
            GroupId(
                id = id,
                name = "加入交流群 0x0" + (index + 1)
            )
        }.toImmutableList()
        friendshipDialogViewState = friendshipDialogViewState.copy(
            visible = true,
            groupIds = groupIds
        )
    }

    private fun dismissFriendshipDialog() {
        friendshipDialogViewState = friendshipDialogViewState.copy(visible = false)
    }

    private fun addFriend(userId: String) {
        viewModelScope.launch {
            val formatUserId = userId.lowercase()
            when (val result = friendshipProvider.addFriend(friendId = formatUserId)) {
                is ActionResult.Success -> {
                    delay(timeMillis = 400)
                    showToast(msg = "添加成功")
                    ChatActivity.navTo(
                        context = context,
                        chat = Chat.PrivateChat(id = formatUserId)
                    )
                    dismissFriendshipDialog()
                }

                is ActionResult.Failed -> {
                    showToast(msg = result.desc)
                }
            }
        }
    }

    private fun joinGroup(groupId: String) {
        viewModelScope.launch {
            when (val result = groupProvider.joinGroup(groupId = groupId)) {
                is ActionResult.Success -> {
                    delay(timeMillis = 800)
                    showToast(msg = "加入成功")
                    groupProvider.refreshJoinedGroupList()
                    dismissFriendshipDialog()
                }

                is ActionResult.Failed -> {
                    showToast(msg = result.desc)
                }
            }
        }
    }

}