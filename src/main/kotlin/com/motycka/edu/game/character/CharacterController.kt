package com.motycka.edu.game.account.rest

import com.motycka.edu.game.account.model.Account
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val accountService: AccountService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody account: Account): Account {
        return accountService.registerAccount(account)
    }
}
