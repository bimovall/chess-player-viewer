package org.example.chess_player_viewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.chess_player_viewer.domain.model.Streamer
import org.example.chess_player_viewer.domain.repository.StreamerRepository
import org.example.chess_player_viewer.utils.Result

class GetStreamerUseCase(private val repository: StreamerRepository) {

    operator fun invoke(): Flow<Result<List<Streamer>>> {
        return repository.getStreamers()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val sortedStreamers = result.data.sortedByDescending { it.isLive }
                        Result.Success(sortedStreamers)
                    }

                    is Result.Error -> result
                }
            }
    }

}