package com.example.starwars.viewmodel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class MovieInfoViewModel(application: Application):AndroidViewModel(application) {
    private val starWarsRepository: StarWarsRepository

    init {
        starWarsRepository = StarWarsRepository()
    }

    private var _planets: MutableLiveData<List<Planet>> = MutableLiveData()
    val planets: LiveData<List<Planet>>
        get() = _planets

    private var _characters: MutableLiveData<List<Person>> = MutableLiveData()
    val characters: LiveData<List<Person>>
        get() = _characters

    fun getPlanetsByUrlList(list: List<String>)
    {
        viewModelScope.launch {
            _planets.value = starWarsRepository.getPlanetsFromUrlList(list)
        }
    }

    fun getPeopleFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            _characters.value=starWarsRepository.getPeopleFromUrlList(list)
        }
    }
}