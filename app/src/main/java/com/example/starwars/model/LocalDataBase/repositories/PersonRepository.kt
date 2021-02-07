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

    fun isFav(name: String) : LiveData<Boolean>
    {
//        Log.d("XD",personDao.findByName(name).value.toString())
       return personDao.isInDatabase(name)
    }

}