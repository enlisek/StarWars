package com.example.starwars.viewmodel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.LocalDataBase.StarWarsDataBase
import com.example.starwars.model.LocalDataBase.entities.FavFilm
import com.example.starwars.model.LocalDataBase.repositories.FilmRepository
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class MovieInfoViewModel(application: Application):AndroidViewModel(application) {
    private val starWarsRepository: StarWarsRepository
    private val filmRepository: FilmRepository =
        FilmRepository(StarWarsDataBase.getDatabase(application).filmDao())
    private var _isF:MutableLiveData<Boolean> = MutableLiveData()
    init {
        starWarsRepository = StarWarsRepository()
        _isF.value = false
    }
    fun isFavourite(title:String){

        viewModelScope.launch{
            _isF.value=filmRepository.isFav(title)
        }


    }
    val isF:LiveData<Boolean>
        get() =_isF
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

    fun add(selectedFilm: Film) {
        var favFilm:FavFilm = FavFilm(
            selectedFilm.title,
            selectedFilm.episode_id,
            selectedFilm.opening_crawl,
            selectedFilm.director,
            selectedFilm.producer,
            selectedFilm.release_date,
        )
        viewModelScope.launch {
            filmRepository.add(favFilm)
        }
    }

    fun remove(selectedFilm: Film) {
            filmRepository.delete(selectedFilm.title)
    }
}