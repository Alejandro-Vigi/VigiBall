package com.example.vigiball.ui.model

data class Character(
    val id: String,
    val name: String,
    val affiliation: String,
    val imageUrl: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String
)

data class CharacterResponse(
    val id: Int,
    val name: String,
    val affiliation: String,
    val image: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String
)