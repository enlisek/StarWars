package com.example.starwars.model.LocalDataBase.repositories

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

    fun allPeople(): LiveData<List<FavPerson>>
    {
        return personDao.allPeople()
    }

    fun ifFav(name: String) : Boolean
    {
        if (personDao.findByName(name) != null)
        {
            return true
        }
        return false

    }
}