package com.example.starwars.model.LocalDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.starwars.model.LocalDataBase.entities.FavPerson

@Dao
interface PersonDao {
    @Insert
    suspend fun insertPerson(person: FavPerson)

    @Delete
    suspend fun deletePerson(person: FavPerson)

    @Query("Select * from person_table")
    fun allPeople(): LiveData<List<FavPerson>>

    @Query ("Select * from person_table where name = :person_name ")
    fun findByName(person_name:String): LiveData<List<FavPerson>>

    @Query ("delete from person_table where name = :person_name ")
    fun deletePersonByName(person_name:String)

    @Query ("select EXISTS(select * from person_table where name = :person_name)")
    fun isInDatabase(person_name:String): LiveData<Boolean>

}