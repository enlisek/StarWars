package com.example.starwars.model.LocalDataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class FavPerson(
    @PrimaryKey val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
)