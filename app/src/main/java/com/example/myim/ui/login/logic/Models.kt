package com.example.myim.ui.login.logic

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue

@Stable
data class LoginPageViewState(
    val userId: TextFieldValue,
    val onUserIdInputChanged: (TextFieldValue) -> Unit,
    val showPanel: Boolean,
    val loading: Boolean
)