package com.example.myim.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.ChatBubbleOutline
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myim.ui.logic.MainPageBottomBarViewState
import com.example.myim.ui.logic.MainPageTab

@Composable
fun MainPageBottomBar(viewState: MainPageBottomBarViewState) {
    Row(
        modifier = Modifier
            .shadow(elevation = 28.dp)
            .background(color = Color(0xFFEFF1F3))
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(height = 54.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (tab in MainPageTab.entries) {
            val selected = viewState.selectedTab == tab
            val icon: ImageVector
            val unreadMessageCount: Long
            when (tab) {
                MainPageTab.Conversation -> {
                    icon = Icons.Rounded.Chat
                    unreadMessageCount = viewState.unreadMessageCount
                }

                MainPageTab.Friendship -> {
                    icon = Icons.Rounded.Person
                    unreadMessageCount = 0
                }

                MainPageTab.Person -> {
                    icon = Icons.Rounded.Home
                    unreadMessageCount = 0
                }

                MainPageTab.New -> {
                    icon = Icons.Rounded.Newspaper
                    unreadMessageCount = 0
                }
            }
            NavigationBarItem(
                icon = icon,
                selected = selected,
                unreadMessageCount = unreadMessageCount,
                onClick = {
                    viewState.onClickTab(tab)
                }
            )
        }
    }
}

@Composable
private fun RowScope.NavigationBarItem(
    icon: ImageVector,
    selected: Boolean,
    unreadMessageCount: Long,
    onClick: () -> Unit
) {
    NavigationBarItem(
        icon = {
            Icon(
                modifier = Modifier
                    .size(size = 22.dp),
                imageVector = icon,
                contentDescription = null
            )
            if (unreadMessageCount > 0) {
                Text(
                    modifier = Modifier
                        .offset(x = 18.dp, y = (-10).dp)
                        .size(size = 22.dp)
                        .background(
                            color = Color(0xFF42A5F5),
                            shape = CircleShape
                        )
                        .wrapContentSize(align = Alignment.Center),
                    text = if (unreadMessageCount > 99) {
                        "99+"
                    } else {
                        unreadMessageCount.toString()
                    },
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFFFFFFF)
                )
            }
        },
        selected = selected,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color(0xFF42A5F5),
            unselectedIconColor = Color(0xFF001018)
        ),
        onClick = onClick
    )
}