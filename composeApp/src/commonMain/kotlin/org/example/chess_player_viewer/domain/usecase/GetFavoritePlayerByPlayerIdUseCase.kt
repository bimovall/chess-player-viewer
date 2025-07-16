package org.example.chess_player_viewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.domain.model.FavoritePlayer
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.example.chess_player_viewer.utils.Result

class GetFavoritePlayerByPlayerIdUseCase(private val repository: ProfileRepository) {

    operator fun invoke(playerId: Long): Flow<Result<FavoritePlayer>> {
        return repository.getFavoritePlayerByPlayerId(playerId)
    }
}