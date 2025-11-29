package com.example.myim.provider

import android.app.Application
import android.util.Log
import com.tencent.mmkv.MMKV

object AccountProvider {

    private const val KEY_GROUP = "AccountGroup"

    private const val KEY_LAST_LOGIN_USER_ID = "keyLastLoginUserId"

    private const val KEY_AUTO_LOGIN = "keyAutoLogin"


    private lateinit var kv: MMKV

    fun init(application: Application) {
        val rootDir = MMKV.initialize(application)
        kv = MMKV.defaultMMKV()

    }

    val lastLoginUserId: String
        get() = kv.decodeString(KEY_LAST_LOGIN_USER_ID, "") ?: ""

    val canAutoLogin: Boolean
        get() = kv.decodeBool(KEY_AUTO_LOGIN, true)

    fun onUserLogin(userId: String) {
        kv.apply {
            encode(KEY_LAST_LOGIN_USER_ID, userId)
            encode(KEY_AUTO_LOGIN, true)
        }
    }

    fun onUserLogout() {
        kv.encode(KEY_AUTO_LOGIN, false)
    }

}