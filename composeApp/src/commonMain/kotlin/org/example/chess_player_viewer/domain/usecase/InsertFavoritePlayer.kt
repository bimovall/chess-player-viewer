package org.example.chess_player_viewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.data.local.entity.FavoritePlayerEntity
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.example.chess_player_viewer.utils.Result

class InsertFavoritePlayer(private val repository: ProfileRepository) {

    operator fun invoke(
        playerId: Long,
        username: String,
        name: String,
        title: String,
        avatar: String
    ): Flow<Result<Long>> {
        return repository.insertFavoritePlayer(
            FavoritePlayerEntity(
                playerId = playerId,
                username = username,
                name = name,
                title = title,
                avatar = avatar
            )
        )
    }
}