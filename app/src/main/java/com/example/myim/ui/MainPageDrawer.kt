package com.example.myim.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cabin
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myim.extend.clickableNoRipple
import com.example.myim.ui.logic.AppTheme.Light
import com.example.myim.ui.logic.AppTheme.Dark
import com.example.myim.ui.logic.AppTheme.Gray
import com.example.myim.ui.logic.MainPageDrawerViewState
import com.example.myim.ui.widgets.AnimateBouncyImage
import com.example.myim.ui.widgets.ComponentImage
import kotlinx.coroutines.launch

@Composable
fun MainPageDrawer(viewState: MainPageDrawerViewState) {
    val coroutineScope = rememberCoroutineScope()
    BackHandler(enabled = viewState.drawerState.isOpen) {
        coroutineScope.launch {
            viewState.drawerState.close()
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.80f)
            .background(color = Color(0xFFFFFFFF))
            .navigationBarsPadding(),
        color = Color.Transparent,
        contentColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ComponentImage(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 20.dp, top = 20.dp)
                    .size(size = 90.dp)
                    .clickableNoRipple {
                        viewState.previewImage(viewState.personProfile.faceUrl)
                    },
                model = viewState.personProfile.faceUrl
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 10.dp, end = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier,
                    text = viewState.personProfile.id,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = Color(0xFF001018)
                )
                Text(
                    modifier = Modifier,
                    text = viewState.personProfile.nickname,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Color(0xFF384F60)
                )
                Text(
                    modifier = Modifier,
                    text = viewState.personProfile.signature,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Color(0xFF384F60)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(space = 6.dp)
            ) {
                SelectableItem(
                    text = "个人资料",
                    icon = Icons.Filled.Cabin,
                    onClick = viewState.updateProfile
                )
                SelectableItem(
                    text = "切换账号",
                    icon = Icons.Filled.Logout,
                    onClick = viewState.logout
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
            )
            Copyright(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }
    }
}

@Composable
private fun SelectableItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .height(height = 55.dp)
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(size = 22.dp),
            imageVector = icon,
            tint = Color(0xFF001018),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(start = 10.dp),
            text = text,
            fontSize = 17.sp,
            lineHeight = 18.sp,
            color =Color(0xFF001018)
        )
    }
}

@Composable
private fun Copyright(modifier: Modifier) {
    val copyright = remember {
        buildString {
            append("versionCode: ")
            append("1.0")
        }
    }
    Text(
        modifier = modifier,
        text = copyright,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Center,
        color =Color(0xFF001018)
    )
}