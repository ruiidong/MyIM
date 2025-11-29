package com.example.myim.ui.new

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.myim.ui.new.logic.NewViewModel
import androidx.core.net.toUri

@Composable
fun NewPage(newViewModel: NewViewModel) {
    val context = LocalContext.current
    val news = newViewModel.pager.collectAsLazyPagingItems()

    LazyColumn() {
        items(news.itemCount) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, news[it]?.articleUrl?.toUri())
                        context.startActivity(intent)
                    }) {
                Text(
                    text = news[it]?.title ?: "",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 10.dp)
                )
                Text(text = news[it]?.description ?: "", fontSize = 12.sp)
                Row(modifier = Modifier.padding(0.dp, 10.dp)) {
                    Text(text = news[it]?.source ?: "", fontSize = 12.sp)
                    Text(
                        text = news[it]?.publishTime ?: "",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(8.dp, 0.dp)
                    )
                }
            }
            Divider(
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color(0x00000000).copy(alpha = 0.08f)
            )
        }
    }
}