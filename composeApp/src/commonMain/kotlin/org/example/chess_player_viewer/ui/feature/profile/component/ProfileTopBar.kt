package org.example.chess_player_viewer.ui.feature.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_add_favorite
import chessplayerviewer.composeapp.generated.resources.ic_arrow_left
import chessplayerviewer.composeapp.generated.resources.ic_favorite
import org.example.chess_player_viewer.ui.component.Avatar
import org.example.chess_player_viewer.ui.feature.profile.FavoriteState
import org.example.chess_player_viewer.utils.ErrorHandler
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(
    collapsedFraction: Float,
    avatar: String,
    name: String,
    onBackPressed: () -> Unit,
    favoriteState: FavoriteState,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Avatar(
                    path = avatar,
                    modifier = Modifier
                        .size(32.dp)
                        .graphicsLayer { alpha = collapsedFraction }
                )

                Box {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.graphicsLayer { alpha = 1f - collapsedFraction }
                    )

                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.graphicsLayer { alpha = collapsedFraction }
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_left),
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            when (favoriteState) {
                is FavoriteState.Error -> {
                    ToggleFavoriteButton(isVisible = false, isFavorite = false, onClick = onToggleFavorite)
                }
                is FavoriteState.Loading -> {
                    CircularProgressIndicator()
                }
                is FavoriteState.Success -> {
                    ToggleFavoriteButton(isVisible = true, isFavorite = favoriteState.isDataAvailable, onClick = onToggleFavorite)
                }
            }

        },
        modifier = modifier
    )
}

@Composable
fun ToggleFavoriteButton(isVisible: Boolean, isFavorite: Boolean, onClick: () -> Unit) {
    if (!isVisible) return

    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(if (isFavorite) Res.drawable.ic_favorite else Res.drawable.ic_add_favorite),
            contentDescription = "Settings"
        )
    }
}