package com.example.starwars.model.LocalDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.starwars.model.LocalDataBase.entities.FavFilm

@Dao
interface FilmDao {
    @Insert
    suspend fun insertFilm(film: FavFilm)

    @Delete
    suspend fun deleteFilm(film:FavFilm)

    @Query("Select * from film_table")
    fun allFilms(): LiveData<List<FavFilm>>
}