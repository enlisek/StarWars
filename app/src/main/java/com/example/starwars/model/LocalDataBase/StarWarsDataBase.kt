package com.example.starwars.model.LocalDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.starwars.model.LocalDataBase.dao.FilmDao
import com.example.starwars.model.LocalDataBase.dao.PersonDao
import com.example.starwars.model.LocalDataBase.dao.PlanetDao
import com.example.starwars.model.LocalDataBase.entities.FavFilm
import com.example.starwars.model.LocalDataBase.entities.FavPerson
import com.example.starwars.model.LocalDataBase.entities.FavPlanet

@Database(entities = [FavFilm::class, FavPerson::class, FavPlanet::class],version = 1,exportSchema = false)
abstract class StarWarsDataBase:RoomDatabase() {

    abstract fun filmDao(): FilmDao
    abstract fun personDao(): PersonDao
    abstract fun planetDao(): PlanetDao


    companion object{
        @Volatile
        private var INSTANCE: StarWarsDataBase?=null

        fun getDatabase(context: Context): StarWarsDataBase {
            val tempInstance= INSTANCE

            if(tempInstance!=null)
                return tempInstance
            else
                synchronized(this)
                {
                    val instance= Room.databaseBuilder(
                        context.applicationContext,
                        StarWarsDataBase::class.java,
                        "my_database"
                    ).build()
                    INSTANCE =instance
                    return instance

                }


        }


    }
}