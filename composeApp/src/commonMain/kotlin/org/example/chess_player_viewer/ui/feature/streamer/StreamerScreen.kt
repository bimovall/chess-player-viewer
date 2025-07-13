package org.example.chess_player_viewer.ui.feature.streamer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.domain.model.Streamer
import org.example.chess_player_viewer.ui.feature.streamer.component.StreamerItem
import org.example.chess_player_viewer.ui.feature.streamer.component.StreamerProfileBottomSheet
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamerScreen(
    viewModel: StreamerViewModel = koinViewModel(),
    onOpenProfile: (username: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetState = rememberModalBottomSheetState()
    val (selectedItem, setSelectedItem) = remember { mutableStateOf<Streamer?>(null) }
    val uriHandler = LocalUriHandler.current
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.getStreamer()
    }

    when (val state = uiState.value) {
        is StreamerUiState.Error -> {
            //TODO add error state later
        }

        is StreamerUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
                            setSelectedItem(item)
                        }
                    )
                }
            }

            if (selectedItem != null) {
                StreamerProfileBottomSheet(
                    selectedItem,
                    bottomSheetState,
                    onRedirectToLiveStream = {
                        uriHandler.openUri(it)
                    },
                    onOpenProfile = {
                        onOpenProfile.invoke(it)
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }

                    },
                    onDismissRequest = {
                        setSelectedItem(null)
                    })
            }
        }
    }


}