package com.example.myim.ui.logic

import android.content.Intent
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.base.models.ActionResult
import com.example.base.models.PersonProfile
import com.example.base.models.ServerConnectState
import com.example.myim.provider.AccountProvider
import com.example.myim.provider.AppThemeProvider
import com.example.myim.ui.base.BaseViewModel
import com.example.myim.ui.preview.PreviewImageActivity
import com.example.myim.ui.profile.ProfileUpdateActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    var loadingDialogVisible by mutableStateOf(value = false)
        private set

    val topBarViewState = MainPageTopBarViewState(
        openDrawer = ::openDrawer
    )

    var bottomBarViewState by mutableStateOf(
        value = MainPageBottomBarViewState(
            selectedTab = MainPageTab.Conversation,
            unreadMessageCount = 0L,
            onClickTab = ::onClickTab
        )
    )
        private set

    var drawerViewState by mutableStateOf(
        value = MainPageDrawerViewState(
            drawerState = DrawerState(initialValue = DrawerValue.Closed),
            appTheme = AppThemeProvider.appTheme,
            personProfile = MyIM.accountProvider.personProfile.value,
            previewImage = ::previewImage,
            logout = ::logout,
            updateProfile = ::updateProfile
        )
    )
        private set

    private val _serverConnectState = MutableStateFlow(value = ServerConnectState.Connected)

    val serverConnectState: SharedFlow<ServerConnectState> = _serverConnectState

    init {
        viewModelScope.launch {
            launch {
                MyIM.accountProvider.personProfile.collect {
                    onPersonProfileChanged(personProfile = it)
                }
            }
            launch {
                MyIM.accountProvider.serverConnectState.collect {
                    _serverConnectState.emit(value = it)
                    if (it == ServerConnectState.Connected) {
                        requestData()
                    }
                }
            }
            launch {
                requestData()
            }
        }
    }

    private fun requestData() {
        MyIM.accountProvider.refreshPersonProfile()
    }

    private fun onClickTab(mainPageTab: MainPageTab) {
        val viewState = bottomBarViewState
        if (viewState.selectedTab != mainPageTab) {
            bottomBarViewState = viewState.copy(selectedTab = mainPageTab)
        }
    }

    private fun onUnreadMessageCountChanged(unreadMessageCount: Long) {
        val viewState = bottomBarViewState
        if (viewState.unreadMessageCount != unreadMessageCount) {
            bottomBarViewState = viewState.copy(unreadMessageCount = unreadMessageCount)
        }
    }

    private fun onPersonProfileChanged(personProfile: PersonProfile) {
        val viewState = drawerViewState
        if (drawerViewState.personProfile != personProfile) {
            drawerViewState = viewState.copy(personProfile = personProfile)
        }
    }

    private fun logout() {
        viewModelScope.launch {
            loadingDialog(visible = true)
            when (val result = MyIM.accountProvider.logout()) {
                is ActionResult.Success -> {
                    AccountProvider.onUserLogout()
                }

                is ActionResult.Failed -> {
                    showToast(msg = result.reason)
                }
            }
            loadingDialog(visible = false)
        }
    }

    private suspend fun openDrawer() {
        drawerViewState.drawerState.open()
    }

    private fun updateProfile() {
        val intent = Intent(context, ProfileUpdateActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private fun previewImage(imageUrl: String) {
        if (imageUrl.isNotBlank()) {
            PreviewImageActivity.navTo(context = context, imageUri = imageUrl)
        }
    }

    private fun AppTheme.nextTheme(): AppTheme {
        val values = AppTheme.entries
        return values.getOrElse(ordinal + 1) {
            values[0]
        }
    }

    private fun loadingDialog(visible: Boolean) {
        loadingDialogVisible = visible
    }

}