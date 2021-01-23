package com.example.starwars.viewmodel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Planet
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class PlanetListViewModel(application: Application):AndroidViewModel(application) {
    private val starWarsRepository: StarWarsRepository
    init {
        starWarsRepository = StarWarsRepository()
    }
    private var _planets: MutableLiveData<List<Planet>> = MutableLiveData()
    val planets: LiveData<List<Planet>>
        get() = _planets

    fun updatePlanets()
    {
        viewModelScope.launch {
            _planets.value=starWarsRepository.getAllPlanets()
        }
    }
}