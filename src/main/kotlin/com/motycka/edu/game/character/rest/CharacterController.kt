package com.motycka.edu.game.character.rest

import com.motycka.edu.game.character.service.CharacterService
import com.motycka.edu.game.account.rest.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/characters")
class CharacterController(
    private val characterService: CharacterService,
    private val accountService: AccountService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCharacter(@RequestBody characterRequest: CharacterCreateRequest): CharacterResponse {
        val accountId = accountService.getCurrentAccountId()
        return characterService.createCharacter(
            characterRequest.toCharacter().copy(accountId = accountId!!)
        ).toCharacterResponse(accountId)
    }

    @GetMapping
    fun getCharacters(): List<CharacterResponse> {
        val accountId = accountService.getCurrentAccountId()
        return characterService.getCharacters(CharactersFilter.DEFAULT)
            .toCharacterResponses(accountId)
    }
}
