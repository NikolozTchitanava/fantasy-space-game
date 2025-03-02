package com.motycka.edu.game.account.rest

import com.motycka.edu.game.account.model.Account

data class AccountResponse(
    val id: Long,
    val name: String,
    val username: String
)

fun Account.toAccountResponse(): AccountResponse {
    return AccountResponse(
        id = this.id,
        name = this.name,
        username = this.username
    )
}
