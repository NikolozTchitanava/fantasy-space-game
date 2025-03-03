package com.motycka.edu.game.character.service

import com.motycka.edu.game.character.model.Character
import com.motycka.edu.game.character.rest.CharacterRepository
import org.springframework.stereotype.Service

@Service
class CharacterService(private val characterRepository: CharacterRepository) {

    fun getCharacters(filter: CharactersFilter): List<Character> {
        return characterRepository.findAll()
    }

    fun getCharacterById(id: Long): Character {
        return characterRepository.findById(id)
            .orElseThrow { RuntimeException("Character not found") }
    }

    fun createCharacter(character: Character): Character {
        return characterRepository.save(character)
    }
}
