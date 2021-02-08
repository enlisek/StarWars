package com.example.starwars.viewmodel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.LocalDataBase.StarWarsDataBase
import com.example.starwars.model.LocalDataBase.entities.FavFilm
import com.example.starwars.model.LocalDataBase.entities.FavPerson
import com.example.starwars.model.LocalDataBase.entities.FavPlanet
import com.example.starwars.model.LocalDataBase.repositories.FilmRepository
import com.example.starwars.model.LocalDataBase.repositories.PersonRepository
import com.example.starwars.model.LocalDataBase.repositories.PlanetRepository
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import kotlinx.coroutines.launch

class FavouritiesViewModel(application: Application):AndroidViewModel(application) {

    lateinit var listOfCharacters: LiveData<List<FavPerson>>
    lateinit var listOfPlanets: LiveData<List<FavPlanet>>
    lateinit var listOfFilms: LiveData<List<FavFilm>>

    private val personRepository: PersonRepository = PersonRepository(StarWarsDataBase.getDatabase(application).personDao())
    private val planetRepository: PlanetRepository
    private val filmRepository: FilmRepository



    init {

        planetRepository = PlanetRepository(StarWarsDataBase.getDatabase(application).planetDao())
        filmRepository = FilmRepository(StarWarsDataBase.getDatabase(application).filmDao())

        updateCharacters()
        updatePlanets()
        updateFilms()
    }

    var selectedCharacter = FavPerson("","","","","","","","","")
    var selectedPlanet = FavPlanet("","","","","","","")
    var selectedFilm = FavFilm("",0,"","","","")


    fun updateCharacters()
    {
        listOfCharacters=personRepository.allPeople()
    }

    fun updatePlanets()
    {
        listOfPlanets=planetRepository.allPlanets()
    }

    fun updateFilms()
    {
        listOfFilms=filmRepository.allFilms()
    }

    fun add(favPerson: FavPerson)
    {
        viewModelScope.launch {
            personRepository.add(favPerson)
        }
    }

    fun delete(favPerson: FavPerson)
    {
        viewModelScope.launch {
            personRepository.delete(favPerson)
        }
    }

    fun add(favPlanet: FavPlanet)
    {
        viewModelScope.launch {
            planetRepository.add(favPlanet)
        }
    }

    fun delete(favPlanet: FavPlanet)
    {
        viewModelScope.launch {
            planetRepository.delete(favPlanet)
        }
    }
    fun add(favFilm: FavFilm)
    {
        viewModelScope.launch {
            filmRepository.add(favFilm)
        }
    }

    fun delete(favFilm: FavFilm)
    {
        viewModelScope.launch {
            filmRepository.delete(favFilm)
        }
    }
}