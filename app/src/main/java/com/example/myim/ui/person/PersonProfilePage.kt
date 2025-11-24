package com.example.myim.ui.person

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.myim.extend.clickableNoRipple
import com.example.myim.extend.scrim
import com.example.myim.ui.person.logic.PersonProfilePageViewState
import com.example.myim.ui.widgets.AnimateBouncyImage
import com.example.myim.ui.widgets.BezierImage
import com.example.myim.ui.widgets.ComponentImage
import kotlin.math.roundToInt

@Composable
fun PersonProfilePage(pageViewState: PersonProfilePageViewState) {
    val personProfile = pageViewState.personProfile
    val faceUrl = personProfile.faceUrl
    val title = personProfile.showName
    val subtitle = personProfile.signature
    val introduction = "ID: ${personProfile.id}"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
//        BezierImage(
//            modifier = Modifier
//                .aspectRatio(ratio = 1f)
//                .scrim(color = Color(0x33000000)),
//            model = faceUrl
//        )
        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ComponentImage(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(size = 100.dp)
                    .clickableNoRipple {
                        pageViewState.previewImage(faceUrl)
                    },
                model = faceUrl,
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                text = title,
                fontSize = 20.sp,
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
        }
    }
}