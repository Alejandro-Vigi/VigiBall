
package com.example.vigiball.ui.model

data class Transformation(
    val id: String,
    val name: String,
    val image: String,
    val ki: String
)

data class Character(
    val id: String,
    val name: String,
    val affiliation: String,
    val imageUrl: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val transformations: List<Transformation> = emptyList()
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
    val description: String,
    val transformations: List<TransformationResponse>? = null
)

data class TransformationResponse(
    val id: Int,
    val name: String,
    val image: String,
    val ki: String
)