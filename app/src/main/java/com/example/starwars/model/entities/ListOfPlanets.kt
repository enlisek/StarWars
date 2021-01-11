package com.example.starwars.model.entities

data class ListOfPlanets(
        val results: List<Planet>,
        val next: String?
)