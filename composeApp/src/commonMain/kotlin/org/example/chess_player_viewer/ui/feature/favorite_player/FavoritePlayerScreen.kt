package org.example.chess_player_viewer.ui.feature.favorite_player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.chess_player_viewer.ui.component.PlayerCard

@Composable
fun FavoritePlayerScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(16) {
            PlayerCard("", "GM", "test")
        }
    }

}