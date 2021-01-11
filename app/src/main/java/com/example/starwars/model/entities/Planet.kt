package com.example.starwars.model.entities

data class Planet(
    val name: String,
    val rotation_period: Int,
    val orbital_period: Int,
    val diameter: Int,
    val climate: String,
    val terrain: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>
)