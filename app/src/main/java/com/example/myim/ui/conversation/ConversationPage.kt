package com.example.myim.ui.conversation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun LazyItemScope.EmptyPage(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillParentMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            text = "Empty",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 68.sp,
            color = Color(0xFF001018)
        )
    }
}