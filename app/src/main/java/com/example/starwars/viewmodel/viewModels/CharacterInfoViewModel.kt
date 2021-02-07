package com.example.starwars.viewmodel.viewModels

import android.app.Application
import android.database.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Planet
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class CharacterInfoViewModel(application: Application):AndroidViewModel(application) {
    private val starWarsRepository: StarWarsRepository
    private var _homeworld: MutableLiveData<Planet> = MutableLiveData()

    init {
        starWarsRepository = StarWarsRepository()

    }
    val homeworld: LiveData<Planet>
        get() = _homeworld


    private var _movies: MutableLiveData<List<Film>> = MutableLiveData()
    val movies: LiveData<List<Film>>
        get() = _movies

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
}