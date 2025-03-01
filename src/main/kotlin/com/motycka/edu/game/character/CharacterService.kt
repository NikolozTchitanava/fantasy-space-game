package com.motycka.edu.game.character

import com.motycka.edu.game.character.model.Character
import com.motycka.edu.game.character.rest.CharactersFilter
import com.motycka.edu.game.account.AccountService
import org.springframework.stereotype.Service

@Service
class CharacterService(
    private val characterRepository: CharacterRepository,
    private val accountService: AccountService
) {

    fun createCharacter(character: Character): Character {
        return characterRepository.insertCharacters(
            accountId = accountService.getCurrentAccountId(),
            character = character
        ) ?: error("Character could not be created.")
    }

    fun getCharacters(filter: CharactersFilter): List<Character> {
        val accountId = accountService.getCurrentAccountId()
        return characterRepository.selectWithFilter(accountId, filter)
    }
}
