package org.example.chess_player_viewer.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_default_avatar
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource

@Composable
fun Avatar(modifier: Modifier = Modifier, path: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(path)
            .crossfade(true)
            .build(),
        contentDescription = "avatar",
        error = {
            Image(painter = painterResource(Res.drawable.ic_default_avatar), "")
        },
        loading = {
            CircularProgressIndicator()
        },
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(CircleShape)

    )
}