package com.example.myim.ui.login.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.example.base.models.ActionResult
import com.example.myim.provider.AccountProvider
import com.example.myim.ui.base.BaseViewModel
import com.example.myim.ui.logic.MyIM
import kotlinx.coroutines.delay

class LoginViewModel : BaseViewModel() {

    var loginPageViewState by mutableStateOf(
        value = LoginPageViewState(
            userId = TextFieldValue(),
            onUserIdInputChanged = ::onUserIdInputChanged,
            showPanel = false,
            loading = false
        )
    )
        private set

    private fun onUserIdInputChanged(input: TextFieldValue) {
        val trimText = input.text.trim()
        if (trimText.length <= 12 && trimText.all {
                it.isLowerCase() || it.isUpperCase()
            }) {
            loginPageViewState = loginPageViewState.copy(userId = input.copy(text = trimText))
        }
    }

    suspend fun tryLogin(): Boolean {
        val lastLoginUserId = AccountProvider.lastLoginUserId
        val userId = TextFieldValue(
            text = lastLoginUserId,
            selection = TextRange(index = lastLoginUserId.length)
        )
        val canAutoLogin = lastLoginUserId.isNotBlank() && AccountProvider.canAutoLogin
        val showPanel: Boolean
        val loading: Boolean
        if (canAutoLogin) {
            showPanel = false
            loading = true
        } else {
            showPanel = true
            loading = false
        }
        loginPageViewState = loginPageViewState.copy(
            userId = userId,
            showPanel = showPanel,
            loading = loading
        )
        return if (canAutoLogin) {
            val isSuccess = login(userId = lastLoginUserId)
            loginPageViewState = loginPageViewState.copy(
                userId = userId,
                showPanel = !isSuccess,
                loading = false
            )
            isSuccess
        } else {
            false
        }
    }

    suspend fun onClickLoginButton(): Boolean {
        loginPageViewState = loginPageViewState.copy(loading = true)
        val isSuccess = login(userId = loginPageViewState.userId.text)
        loginPageViewState = loginPageViewState.copy(loading = false)
        return isSuccess
    }

    private suspend fun login(userId: String): Boolean {
        val formatUserId = userId.lowercase()
        return when (val result = MyIM.accountProvider.login(userId = formatUserId)) {
            is ActionResult.Success -> {
                AccountProvider.onUserLogin(userId = formatUserId)
                delay(timeMillis = 250)
                true
            }

            is ActionResult.Failed -> {
                showToast(msg = result.desc)
                false
            }
        }
    }

}