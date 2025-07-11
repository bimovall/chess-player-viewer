package org.example.chess_player_viewer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.domain.model.Streamer
import org.example.chess_player_viewer.domain.model.mapToStreamer
import org.example.chess_player_viewer.domain.repository.StreamerRepository
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.mapSuccess

class StreamerRepositoryImpl(private val remoteSource: RemoteSource) : StreamerRepository {
    override fun getStreamers(): Flow<Result<List<Streamer>>> {
        return flow {
            emit(remoteSource.getStreamers().mapSuccess { dto ->
                dto.streamers.map {
                    it.mapToStreamer()
                }
            })
        }
    }
}