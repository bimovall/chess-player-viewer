package org.example.chess_player_viewer.ui.feature.streamer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.domain.usecase.GetStreamerUseCase
import org.example.chess_player_viewer.utils.Result

class StreamerViewModel(private val getStreamerUseCase: GetStreamerUseCase) : ViewModel() {

    private var _uiState = MutableStateFlow<StreamerUiState>(StreamerUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private var hasStreamerCalled = false

    fun getStreamer() {
        if (hasStreamerCalled) return
        hasStreamerCalled = true
        viewModelScope.launch {
            getStreamerUseCase().collect {
                _uiState.update { state ->
                    when (it) {
                        is Result.Error -> {
                            StreamerUiState.Error(it.cause)
                        }

                        is Result.Success -> {
                            StreamerUiState.Success(it.data)
                        }
                    }
                }
            }
        }
    }
}