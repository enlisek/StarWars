package com.example.starwars.model.LocalDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.starwars.model.LocalDataBase.entities.FavPlanet

@Dao
interface PlanetDao {
    @Insert
    suspend fun insertPlanet(planet: FavPlanet)

    @Delete
    suspend fun deletePlanet(planet: FavPlanet)

    @Query("Select * from planet_table")
    fun allPlanets(): LiveData<List<FavPlanet>>
}