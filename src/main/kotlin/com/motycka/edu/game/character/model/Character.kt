package com.motycka.edu.game.character.model

import jakarta.persistence.*

@Entity
@Table(name = "characters")
data class Character(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val health: Int,
    val attackPower: Int,
    val stamina: Int?,
    val defensePower: Int?,
    val mana: Int?,
    val healingPower: Int?,
    @Enumerated(EnumType.STRING)
    val characterClass: `CharacterClass.kt`,
    val level: Int = 1,
    val experience: Int = 0,
    val accountId: Long
)
