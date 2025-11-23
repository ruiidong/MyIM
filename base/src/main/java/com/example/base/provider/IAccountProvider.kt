package com.example.base.provider

import android.app.Application
import com.example.base.models.ActionResult
import com.example.base.models.PersonProfile
import com.example.base.models.ServerConnectState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IAccountProvider {

    val personProfile: StateFlow<PersonProfile>

    val serverConnectState: SharedFlow<ServerConnectState>

    fun init(application: Application)

    suspend fun login(userId: String): ActionResult

    suspend fun logout(): ActionResult

    suspend fun getPersonProfile(): PersonProfile?

    fun refreshPersonProfile()

    suspend fun updatePersonProfile(
        faceUrl: String,
        nickname: String,
        signature: String
    ): ActionResult

}