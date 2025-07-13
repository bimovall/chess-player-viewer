package org.example.chess_player_viewer.ui.feature.streamer.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_redirect
import org.example.chess_player_viewer.domain.model.Streamer
import org.example.chess_player_viewer.ui.component.Avatar
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamerProfileBottomSheet(
    streamer: Streamer,
    state: SheetState,
    onDismissRequest: () -> Unit,
    onRedirectToLiveStream: (url: String) -> Unit,
    onOpenProfile: (username: String) -> Unit,
    modifier: Modifier = Modifier
) {

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = state,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            item {
                Header(streamer.avatar, streamer.username, onOpenProfile = onOpenProfile)
            }
            items(streamer.platforms) {
                PlatformItem(it, onClick = onRedirectToLiveStream)
            }
        }
    }

}

@Composable
fun PlatformItem(
    platform: Streamer.StreamPlatform,
    onClick: (url: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable {
            onClick(platform.liveUrl.ifEmpty { platform.channelUrl })
        }.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(platform.imageResource),
            contentDescription = "icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            platform.platformName,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.weight(1F)
        )
        Icon(
            painter = painterResource(Res.drawable.ic_redirect),
            contentDescription = "redirect",
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun Header(imagePath: String, username: String, onOpenProfile: (username: String) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier.clickable {
        onOpenProfile(username)
    }, verticalAlignment = Alignment.CenterVertically) {
        Avatar(path = imagePath, modifier = Modifier.size(36.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            username,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1F)
        )
    }
}