package com.example.myim.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myim.ui.conversation.ConversationPage
import com.example.myim.ui.conversation.logic.ConversationViewModel
import com.example.myim.ui.friendship.FriendshipDialog
import com.example.myim.ui.friendship.FriendshipPage
import com.example.myim.ui.friendship.logic.FriendshipViewModel
import com.example.myim.ui.logic.MainPageTab
import com.example.myim.ui.logic.MainViewModel
import com.example.myim.ui.person.PersonProfilePage
import com.example.myim.ui.person.logic.PersonProfileViewModel
import com.example.myim.ui.theme.WindowInsetsEmpty
import com.example.myim.ui.widgets.LoadingDialog

@Composable
fun MainPage(
    mainViewModel: MainViewModel,
    conversationViewModel: ConversationViewModel,
    friendshipViewModel: FriendshipViewModel,
    personProfileViewModel: PersonProfileViewModel
) {
    ModalNavigationDrawer(
        modifier = Modifier
            .fillMaxSize(),
        drawerState = mainViewModel.drawerViewState.drawerState,
        drawerContent = {
            MainPageDrawer(viewState = mainViewModel.drawerViewState)
        },
        content = {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                containerColor = Color(0xFFFFFFFF),
                contentWindowInsets = WindowInsetsEmpty,
                topBar = {
                    if (mainViewModel.bottomBarViewState.selectedTab != MainPageTab.Person) {
                        MainPageTopBar(
                            viewState = mainViewModel.topBarViewState,
                            showFriendshipDialog = {
                                friendshipViewModel.showFriendshipDialog()
                            }
                        )
                    }
                },
                bottomBar = {
                    MainPageBottomBar(viewState = mainViewModel.bottomBarViewState)
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = innerPadding)
                ) {
                    when (mainViewModel.bottomBarViewState.selectedTab) {
                        MainPageTab.Conversation -> {
                            ConversationPage(pageViewState = conversationViewModel.pageViewState)
                        }

                        MainPageTab.Friendship -> {
                            FriendshipPage(pageViewState = friendshipViewModel.pageViewState)
                        }

                        MainPageTab.Person -> {
                            PersonProfilePage(pageViewState = personProfileViewModel.pageViewState)
                        }
                    }
                }
            }
            FriendshipDialog(viewState = friendshipViewModel.friendshipDialogViewState)
        }
    )
    LoadingDialog(visible = mainViewModel.loadingDialogVisible)
}