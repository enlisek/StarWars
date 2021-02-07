package com.example.starwars.model.LocalDataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planet_table")
data class FavPlanet(
    @PrimaryKey val name: String,
    val rotation_period: String,
    val orbital_period: String,
    val diameter: String,
    val climate: String,
    val terrain: String,
    val population: String
)