package com.example.proxy

import com.example.base.models.PersonProfile
import com.tencent.imsdk.v2.V2TIMManager
import com.tencent.imsdk.v2.V2TIMUserFullInfo
import com.tencent.imsdk.v2.V2TIMValueCallback
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object Converters {

    suspend fun getSelfProfileOrigin(): V2TIMUserFullInfo? {
        return suspendCancellableCoroutine { continuation ->
            V2TIMManager.getInstance().getUsersInfo(
                listOf(V2TIMManager.getInstance().loginUser),
                object : V2TIMValueCallback<List<V2TIMUserFullInfo>> {
                    override fun onSuccess(t: List<V2TIMUserFullInfo>) {
                        continuation.resume(value = t[0])
                    }

                    override fun onError(code: Int, desc: String?) {
                        continuation.resume(value = null)
                    }
                })
        }
    }

    suspend fun getSelfProfile(): PersonProfile? {
        val profile = getSelfProfileOrigin()
        return if (profile == null) {
            null
        } else {
            PersonProfile(
                id = profile.userID ?: "",
                nickname = profile.nickName ?: "",
                remark = profile.nickName ?: "",
                faceUrl = profile.faceUrl ?: "",
                addTime = 0,
                signature = profile.selfSignature ?: ""
            )
        }
    }
}