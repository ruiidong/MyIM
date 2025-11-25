package com.example.myim.ui.logic

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Stable
import com.example.base.models.PersonProfile

@Stable
enum class MainPageTab {
    Conversation,
    Friendship,
    Person;
}

@Stable
data class MainPageDrawerViewState(
    val drawerState: DrawerState,
    val personProfile: PersonProfile,
    val appTheme: AppTheme,
    val previewImage: (String) -> Unit,
    val updateProfile: () -> Unit,
    val logout: () -> Unit
)

@Stable
data class MainPageTopBarViewState(
    val openDrawer: suspend () -> Unit
)

@Stable
data class MainPageBottomBarViewState(
    val selectedTab: MainPageTab,
    val unreadMessageCount: Long,
    val onClickTab: (MainPageTab) -> Unit
)

@Stable
enum class AppTheme {
    Light,
    Dark,
    Gray;
}