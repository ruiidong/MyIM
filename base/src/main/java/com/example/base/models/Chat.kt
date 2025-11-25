package com.example.base.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.compose.runtime.Stable

@Stable
sealed class Chat(open val id: String) : Parcelable {

    @Parcelize
    class PrivateChat(override val id: String) : Chat(id = id)

    @Parcelize
    class GroupChat(override val id: String) : Chat(id = id)

}