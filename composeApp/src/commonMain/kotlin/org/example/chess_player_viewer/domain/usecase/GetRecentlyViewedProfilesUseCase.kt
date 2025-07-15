package org.example.chess_player_viewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.example.chess_player_viewer.utils.Result

class GetRecentlyViewedProfilesUseCase(private val repository: ProfileRepository) {

    operator fun invoke(count: Int = 15): Flow<Result<List<Profile>>> {
        return repository.getRecentlyViewedProfiles(count)
    }
}