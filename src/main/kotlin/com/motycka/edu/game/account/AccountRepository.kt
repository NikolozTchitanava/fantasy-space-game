package com.motycka.edu.game.account.rest

import com.motycka.edu.game.account.model.Account
import com.motycka.edu.game.account.model.AccountId
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException
import kotlin.collections.firstOrNull

private val logger = KotlinLogging.logger {}

@Repository
class AccountRepository(
    private val jdbcTemplate: JdbcTemplate
) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun selectById(id: AccountId): Account? {
        logger.debug { "Selecting user by id $id" }
        return jdbcTemplate.query(
            "SELECT * FROM accounts WHERE id = ?;",
            ::rowMapper,
            id
        ).firstOrNull()
    }

    fun selectByUsername(username: String): Account? {
        logger.debug { "Selecting user by username ***" }
        return jdbcTemplate.query(
            "SELECT * FROM accounts WHERE username = ?;",
            ::rowMapper,
            username
        ).firstOrNull()
    }

    fun insertAccount(account: Account): Account? {
        logger.debug { "Inserting new user ${account.copy(password = "***")}" }

        val hashedPassword = passwordEncoder.encode(account.password)

        return jdbcTemplate.query(
            """
                SELECT * FROM FINAL TABLE (
                    INSERT INTO accounts (name, username, password) 
                    VALUES (?, ?, ?)
                );
            """.trimIndent(),
            ::rowMapper,
            account.name,
            account.username,
            hashedPassword
        ).firstOrNull()
    }

    @Throws(SQLException::class)
    private fun rowMapper(rs: ResultSet, i: Int): Account {
        return Account(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("username"),
            rs.getString("password")
        )
    }
}
