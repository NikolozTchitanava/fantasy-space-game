package com.motycka.edu.game.account.rest

import com.motycka.edu.game.account.model.Account

data class AccountRegistrationRequest(
    val name: String,
    val username: String,
    val password: String
) {
    fun toAccount(): Account {
        return Account(
            name = this.name,
            username = this.username,
            password = this.password
        )
    }
}
