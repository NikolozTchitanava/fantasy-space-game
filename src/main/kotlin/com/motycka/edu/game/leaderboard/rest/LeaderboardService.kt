package com.motycka.edu.game.leaderboard.rest

import com.motycka.edu.game.leaderboard.model.LeaderboardEntry
import com.motycka.edu.game.character.model.CharacterClass
import org.springframework.stereotype.Service

@Service
class LeaderboardService(private val leaderboardRepository: LeaderboardRepository) {

    fun getLeaderboard(characterClass: String?): List<LeaderboardEntry> {
        return if (characterClass != null) {
            val classEnum = CharacterClass.valueOf(characterClass.uppercase())
            leaderboardRepository.findByCharacterCharacterClass(classEnum)
        } else {
            leaderboardRepository.findAll()
        }
    }
}
