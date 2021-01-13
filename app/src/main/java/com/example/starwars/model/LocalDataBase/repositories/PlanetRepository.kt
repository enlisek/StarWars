package com.example.starwars.model.LocalDataBase.repositories

import androidx.lifecycle.LiveData
import com.example.starwars.model.LocalDataBase.dao.PlanetDao
import com.example.starwars.model.LocalDataBase.entities.FavPlanet

class PlanetRepository(private val planetDao: PlanetDao) {

    suspend fun add(planet: FavPlanet) {
        planetDao.insertPlanet(planet)
    }

    suspend fun delete(planet: FavPlanet){
        planetDao.deletePlanet(planet)
    }

    fun allPlanets(): LiveData<List<FavPlanet>>
    {
        return planetDao.allPlanets()
    }
}