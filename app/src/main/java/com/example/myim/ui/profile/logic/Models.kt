package com.example.myim.ui.profile.logic

import androidx.compose.runtime.Stable
import com.example.base.models.PersonProfile

@Stable
data class ProfileUpdatePageViewStata(
    val personProfile: PersonProfile?,
    val onNicknameChanged: (String) -> Unit,
    val onSignatureChanged: (String) -> Unit,
    val onAvatarUrlChanged: (String) -> Unit,
    val confirmUpdate: () -> Unit
)