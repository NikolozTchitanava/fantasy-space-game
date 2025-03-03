package com.motycka.edu.game.character.model

data class Character(
    val id: Long? = null,
    val name: String,
    val race: String,
    val characterClass: String,
    val level: Int = 1,
    val healthPoints: Int = 100,
    val manaPoints: Int = 50,
    val experience: Int = 0,
    val accountId: String? = null
)
