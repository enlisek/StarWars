package com.example.starwars.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.LocalDataBase.StarWarsDataBase
import com.example.starwars.model.LocalDataBase.repositories.PersonRepository
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application)  {
    private val starWarsRepository: StarWarsRepository = StarWarsRepository()
    var listOfPeople = listOf<Person>()
    var listOfPlanets = listOf<Planet>()
    var selectedCharacter = Person("","","","","","","","","",listOf())
    var selectedPlanet = Planet("","","","","","","",listOf(),listOf())
    var selectedFilm = Film("",0,"","","","",listOf(),listOf())


    fun getPeopleFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            listOfPeople = starWarsRepository.getPeopleFromUrlList(list)
        }
    }




}