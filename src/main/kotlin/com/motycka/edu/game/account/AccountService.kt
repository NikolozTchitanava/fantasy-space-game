package com.motycka.edu.game.account.rest

import com.motycka.edu.game.account.model.Account
import com.motycka.edu.game.account.model.AccountId
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun getAccount(): Account {
        logger.debug { "Getting current user" }
        val currentUserId = getCurrentAccountId()
        return accountRepository.selectById(currentUserId)
            ?: throw UsernameNotFoundException("Account with ID $currentUserId not found")
    }

    fun getCurrentAccountId(): AccountId? {
        val authentication = SecurityContextHolder.getContext().authentication ?: return null
        val principal = authentication.principal
        return if (principal is UserDetails) {
            accountRepository.selectByUsername(principal.username)?.id
                ?: throw UsernameNotFoundException("User ${principal.username} not found")
        } else {
            throw IllegalStateException("Unknown principal type: $principal")
        }
    }

    fun findByUsername(username: String): Account? {
        logger.debug { "Fetching user by username: $username" }
        return accountRepository.selectByUsername(username)
    }

    fun createAccount(account: Account): Account {
        logger.debug { "Creating new user: ${account.username}" }
        val hashedPassword = passwordEncoder.encode(account.password)
        val newAccount = account.copy(password = hashedPassword)

        return accountRepository.insertAccount(newAccount) ?: throw RuntimeException(CREATE_ERROR)
    }

    companion object {
        const val CREATE_ERROR = "Account could not be created."
    }
}
