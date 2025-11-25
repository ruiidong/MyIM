package com.example.base.provider

import com.example.base.models.ActionResult
import com.example.base.models.PersonProfile
import kotlinx.coroutines.flow.SharedFlow

interface IFriendshipProvider {

    val friendList: SharedFlow<List<PersonProfile>>

    fun refreshFriendList()

    suspend fun getFriendProfile(friendId: String): PersonProfile?

    suspend fun setFriendRemark(friendId: String, remark: String): ActionResult

    suspend fun addFriend(friendId: String): ActionResult

    suspend fun deleteFriend(friendId: String): ActionResult

}