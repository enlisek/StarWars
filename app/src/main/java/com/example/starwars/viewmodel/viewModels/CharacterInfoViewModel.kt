package com.example.starwars.viewmodel.viewModels

import android.app.Application

import android.database.Observable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.LocalDataBase.StarWarsDataBase
import com.example.starwars.model.LocalDataBase.entities.FavPerson
import com.example.starwars.model.LocalDataBase.repositories.PersonRepository
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharacterInfoViewModel(application: Application):AndroidViewModel(application) {

    val homeworld: LiveData<Planet>
        get() = _homeworld

    val isF:LiveData<Boolean>
        get() =_isF

    val movies: LiveData<List<Film>>
        get() = _movies

    private val starWarsRepository: StarWarsRepository=StarWarsRepository()
    private var _homeworld: MutableLiveData<Planet> = MutableLiveData()
    private val personRepository:PersonRepository =
        PersonRepository(StarWarsDataBase.getDatabase(application).personDao())
    private var _isF:MutableLiveData<Boolean> = MutableLiveData()
    private var _movies: MutableLiveData<List<Film>> =MutableLiveData()


    init
    {
        _isF.value = false
    }


    fun getFilmsFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            _movies.value = starWarsRepository.getFilmsFromUrlList(list)
        }
    }

    fun setPlanetFromUrl(url: String)
    {
        viewModelScope.launch {
            _homeworld.value=starWarsRepository.getPlanetByUrl(url)
        }
    }

    fun isFavourite(name:String)
    {

        viewModelScope.launch{
            _isF.value=personRepository.isFav(name)
        }
    }

    fun add(person:Person)
    {
        var favPerson = FavPerson(
        person.name,
        person.height,
        person.mass,
        person.hair_color,
        person.skin_color,
        person.eye_color,
        person.birth_year,
        person.gender,
        homeworld.value!!.name
        )
        viewModelScope.launch{
            personRepository.add(favPerson)
        }
    }

    fun remove(person:Person)
    {
        personRepository.delete(person.name)
    }


}