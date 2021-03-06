package com.example.starwars.model.LocalDataBase.repositories

import androidx.lifecycle.LiveData
import com.example.starwars.model.LocalDataBase.dao.FilmDao
import com.example.starwars.model.LocalDataBase.entities.FavFilm

class FilmRepository(private val filmDao: FilmDao) {


    suspend fun add(film: FavFilm) {
        filmDao.insertFilm(film)
    }

    suspend fun delete(film: FavFilm){
        filmDao.deleteFilm(film)
    }

   fun delete(title:String){
        filmDao.deleteMovieByTitle(title)
    }

    suspend fun isFav(title: String):Boolean{
        return filmDao.isInDatabase(title)
    }

    fun allFilms(): LiveData<List<FavFilm>>
    {
        return filmDao.allFilms()
    }
}