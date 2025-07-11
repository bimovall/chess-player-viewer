package org.example.chess_player_viewer.ui.feature.streamer.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.example.chess_player_viewer.ui.component.BadgeItem
import org.example.chess_player_viewer.ui.style.Color.BgLeaderboardItemColor

@Composable
fun StreamerItem(
    avatar: String,
    username: String,
    isLive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = BgLeaderboardItemColor,
        ),
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {

            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(avatar)
                    .crossfade(true)
                    .build(),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp).height(36.dp).width(36.dp)
                    .clip(CircleShape),

                )
            Text(
                username,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
                    .weight(1f)
            )

            if (isLive) {
                BadgeItem("Live", modifier = Modifier.align(Alignment.CenterVertically))
            }

        }
    }
}