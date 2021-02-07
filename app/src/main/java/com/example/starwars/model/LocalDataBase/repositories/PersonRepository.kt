package com.example.starwars.model.LocalDataBase.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.starwars.model.LocalDataBase.dao.PersonDao
import com.example.starwars.model.LocalDataBase.entities.FavPerson

class PersonRepository(private val personDao: PersonDao) {


    suspend fun add(person: FavPerson) {
        personDao.insertPerson(person)
    }

    suspend fun delete(person: FavPerson){
        personDao.deletePerson(person)
    }

    fun delete(name: String){
        personDao.deletePersonByName(name)
    }

    fun allPeople(): LiveData<List<FavPerson>>
    {
        return personDao.allPeople()
    }

    suspend fun isFav(name: String) : Boolean
    {
       return personDao.isInDatabase(name)
    }

}