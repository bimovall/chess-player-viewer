package org.example.chess_player_viewer.ui.feature.streamer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.chess_player_viewer.ui.feature.streamer.component.StreamerItem

@Composable
fun StreamerScreen(modifier: Modifier = Modifier) {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        repeat(20) { i ->
            item {
                StreamerItem(
                    "",
                    "vall",
                    i % 2 == 0,
                    onClick = {

                    }
                )
            }
        }
    }
}