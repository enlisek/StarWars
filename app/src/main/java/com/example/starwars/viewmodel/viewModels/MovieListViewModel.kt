package com.example.starwars.viewmodel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.entities.Film
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class MovieListViewModel(application: Application): AndroidViewModel(application) {
    private val starWarsRepository: StarWarsRepository
    init {
        starWarsRepository = StarWarsRepository()
    }
    private var _films: MutableLiveData<List<Film>> = MutableLiveData()
    val films: LiveData<List<Film>>
    get() = _films

    fun updateFilms()
    {
        viewModelScope.launch {
            _films.value=starWarsRepository.getAllFilms().results
        }
    }



}