package com.example.starwars.model.entities

data class Film(
    val title: String,
    val episode_id: Int,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val characters: List<Person>,
    val planets: List<Planet>
)
