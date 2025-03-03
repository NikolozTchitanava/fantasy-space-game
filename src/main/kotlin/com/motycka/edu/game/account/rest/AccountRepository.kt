package com.motycka.edu.game.account.rest

import com.motycka.edu.game.account.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByUsername(username: String): Account?
}
