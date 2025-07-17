package org.example.chess_player_viewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.example.chess_player_viewer.utils.Result

class DeleteFavoritePlayerUseCase(private val repository: ProfileRepository) {

    operator fun invoke(username: String): Flow<Result<Boolean>> {
        return repository.deleteFavoritePlayer(username = username)
    }
}