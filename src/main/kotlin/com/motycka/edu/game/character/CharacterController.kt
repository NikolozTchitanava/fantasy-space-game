package com.motycka.edu.game.character

import com.motycka.edu.game.character.CharacterService
import com.motycka.edu.game.character.rest.CharacterCreateRequest
import com.motycka.edu.game.character.rest.CharacterResponse
import com.motycka.edu.game.character.rest.CharactersFilter

@RestController
@RequestMapping("/api/characters")
class CharacterController(
    private val characterService: CharacterService,
    private val accountService: AccountService
) {

    @PostMapping
    fun postCharacter(
        @RequestBody character: CharacterCreateRequest
    ): CharacterResponse {
        val accountId = accountService.getCurrentAccountId()
        return characterService.createCharacter(
            character = character.toCharacter(
                accountId = accountId
            )
        ).toCharacterResponse(accountId)
    }

    @GetMapping
    fun getCharacters(): List<CharacterResponse> {
        val accountId = accountService.getCurrentAccountId()
        return characterService.getCharacters(
            filter = CharactersFilter.DEFAULT
        ).toCharacterResponses(accountId)
    }

}