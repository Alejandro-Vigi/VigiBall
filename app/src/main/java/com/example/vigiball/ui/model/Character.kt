
package com.example.vigiball.ui.model

/*
* Spanish:
* Estas son las clases de datos que definen cómo se estructuran los personajes y transformaciones,
* la clase Character tiene todos los detalles básicos como nombre, raza, género, etc, además de una
* lista de transformaciones, las respuestas de la API vienen en CharacterResponse y TransformationResponse
* pero luego se mapean a Character y Transformation para que sean más fáciles de usar en la app, lo bueno
* es que si no hay transformaciones devuelve una lista vacía por defecto.
*
* English:
* These are the data classes that define how characters and transformations are structured, the Character
* class has all basic details like name, race, gender etc, plus a transformations list, the API responses
* come as CharacterResponse and TransformationResponse but then get mapped to Character and Transformation
* to be easier to use in the app, the cool part is that if there are no transformations it returns an empty
* list by default.
*/

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