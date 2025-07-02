package org.example.chess_player_viewer.ui.feature.leaderboard.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun PodiumAvatar(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data("https://cdn.antaranews.com/cache/1200x800/2023/06/18/20230618_080945.jpg")
                .crossfade(true)
                .build(),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(80.dp).width(80.dp)
                .clip(CircleShape)
                .border(border = BorderStroke(1.dp, Color.Red), shape = CircleShape),

            )

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
                .size(16.dp)
                .offset(y = 6.dp)
                .clip(CircleShape)
                .background(Color.Red)
        ) {
            Text(
                "1", style = MaterialTheme.typography.labelSmall.copy(
                    color = Color.White,
                    fontSize = 12.sp
                ), modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}