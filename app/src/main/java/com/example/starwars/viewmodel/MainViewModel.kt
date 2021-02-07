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
    var listOfPeople = listOf<Person>()
    var listOfPlanets = listOf<Planet>()
 //   var listOfFilms = listOf<Film>()


    private val starWarsRepository: StarWarsRepository
    init {
        starWarsRepository = StarWarsRepository()
    }
    var selectedCharacter = Person("","","","","","","","","",listOf())
    var selectedPlanet = Planet("","","","","","","",listOf(),listOf())
    var selectedFilm = Film("",0,"","","","",listOf(),listOf())
 //   var homeworld = Planet("","","","","","","",listOf(),listOf())


//    fun setPlanetFromUrl(url: String)
//    {
//        viewModelScope.launch {
//            selectedPlanet=starWarsRepository.getPlanetByUrl(url)
//        }
//    }

//    fun setHomeworld(url: String)
//    {
//        viewModelScope.launch {
//            homeworld=starWarsRepository.getPlanetByUrl(url)
//        }
//    }


    fun getPeopleFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            listOfPeople = starWarsRepository.getPeopleFromUrlList(list)
        }
    }

    fun getPlanetsFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            listOfPlanets = starWarsRepository.getPlanetsFromUrlList(list)
        }
    }

//    fun getFilmsFromUrlList(list: List<String>)
//    {
//        viewModelScope.launch {
//            listOfFilms = starWarsRepository.getFilmsFromUrlList(list)
//        }
//    }

}