package com.example.starwars.model.LocalDataBase.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_table")
data class FavFilm(
    @PrimaryKey val title: String,
    val episode_id: Int,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val release_date: String
)