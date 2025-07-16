package org.example.chess_player_viewer.ui.feature.favorite_player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.chess_player_viewer.ui.component.PlayerCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritePlayerScreen(
    viewModel: FavoritePlayerViewModel = koinViewModel(),
    onRedirectToProfile: (username: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllFavoritePlayer()
    }

    when (uiState.value) {
        is FavoritePlayerUiState.Error -> {
            //TODO add error state
        }
        is FavoritePlayerUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is FavoritePlayerUiState.Success -> {
            LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items((uiState.value as FavoritePlayerUiState.Success).data) {
                    PlayerCard(it.avatar, it.title, it.username, onClick = {
                        onRedirectToProfile(it.username)
                    })
                }
            }
        }
    }


}