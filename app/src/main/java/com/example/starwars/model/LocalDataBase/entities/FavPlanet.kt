package com.example.starwars.model.LocalDataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planet_table")
data class FavPlanet(
    @PrimaryKey val name: String,
    val rotation_period: Int,
    val orbital_period: Int,
    val diameter: Int,
    val climate: String,
    val terrain: String,
    val population: String
)