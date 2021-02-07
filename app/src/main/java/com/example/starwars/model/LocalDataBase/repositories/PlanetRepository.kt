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

    fun delete(name:String){
        planetDao.deletePlanetByName(name)
    }

    fun allPlanets(): LiveData<List<FavPlanet>>
    {
        return planetDao.allPlanets()
    }
    suspend fun isFav(name: String):Boolean
    {
        return planetDao.isInDatabase(name)
    }
}