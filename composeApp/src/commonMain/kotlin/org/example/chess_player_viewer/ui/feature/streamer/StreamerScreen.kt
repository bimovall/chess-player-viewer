package org.example.chess_player_viewer.ui.feature.streamer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.chess_player_viewer.ui.feature.streamer.component.StreamerItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StreamerScreen(
    viewModel: StreamerViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getStreamer()
    }

    when (val state = uiState.value) {
        is StreamerUiState.Error -> {
            //TODO add error state later
        }

        is StreamerUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        is StreamerUiState.Success -> {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
                items(state.data, key = {
                    it.username
                }) { item ->
                    StreamerItem(
                        item.avatar,
                        item.username,
                        item.isLive,
                        onClick = {
                            //TODO add bottom sheet
                        }
                    )
                }
            }
        }
    }
}