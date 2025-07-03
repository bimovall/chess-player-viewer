package org.example.chess_player_viewer.ui.feature.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.data.remote.RemoteSource

class LeaderboardViewModel(val remoteSource: RemoteSource): ViewModel() {

    fun test() {
        viewModelScope.launch {
            remoteSource.getLeaderboard()
        }
    }
}