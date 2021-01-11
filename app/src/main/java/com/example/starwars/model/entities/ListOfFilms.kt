package com.example.starwars.model.entities

data class ListOfFilms(
        val results: List<Film>,
        val next: String?
)