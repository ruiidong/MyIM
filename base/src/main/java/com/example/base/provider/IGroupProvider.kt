package com.example.base.provider

import com.example.base.models.ActionResult
import com.example.base.models.GroupMemberProfile
import com.example.base.models.GroupProfile
import kotlinx.coroutines.flow.SharedFlow

interface IGroupProvider {

    val joinedGroupList: SharedFlow<List<GroupProfile>>

    fun refreshJoinedGroupList()

    suspend fun joinGroup(groupId: String): ActionResult

    suspend fun quitGroup(groupId: String): ActionResult

    suspend fun getGroupInfo(groupId: String): GroupProfile?

    suspend fun getGroupMemberList(groupId: String): List<GroupMemberProfile>

    suspend fun setAvatar(groupId: String, avatarUrl: String): ActionResult

    suspend fun transferGroupOwner(groupId: String, newOwnerUserID: String): ActionResult

}