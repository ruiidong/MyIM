package com.example.myim.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.myim.extend.clickableNoRipple
import com.example.myim.extend.scrim
import com.example.myim.ui.preview.PreviewImageActivity

@Composable
fun ProfilePanel(
    title: String,
    subtitle: String,
    introduction: String,
    avatarUrl: String,
    content: @Composable BoxScope.() -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ComponentImage(
                modifier = Modifier
                    .size(size = 100.dp)
                    .clickableNoRipple {
                        if (avatarUrl.isNotBlank()) {
                            PreviewImageActivity.navTo(context = context, imageUri = avatarUrl)
                        }
                    },
                model = avatarUrl
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                text = title,
                fontSize = 18.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                text = subtitle,
                fontSize = 15.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                text = introduction,
                fontSize = 15.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
            )
            Box(
                modifier = Modifier
                    .zIndex(zIndex = 1f)
                    .padding(top = 60.dp),
                content = content
            )
        }
    }
}