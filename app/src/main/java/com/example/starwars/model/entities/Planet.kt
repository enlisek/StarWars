package com.example.starwars.model.entities

data class Planet(
    val name: String,
    val rotation_period: String,
    val orbital_period: String,
    val diameter: String,
    val climate: String,
    val terrain: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>
)