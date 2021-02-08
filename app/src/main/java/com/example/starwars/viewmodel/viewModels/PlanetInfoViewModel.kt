package com.example.starwars.viewmodel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.LocalDataBase.StarWarsDataBase
import com.example.starwars.model.LocalDataBase.StarWarsDataBase.Companion.getDatabase
import com.example.starwars.model.LocalDataBase.entities.FavPlanet
import com.example.starwars.model.LocalDataBase.repositories.PersonRepository
import com.example.starwars.model.LocalDataBase.repositories.PlanetRepository
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class PlanetInfoViewModel(application: Application):AndroidViewModel(application) {

    val isF:LiveData<Boolean>
        get() =_isF

    val movies: LiveData<List<Film>>
        get() = _movies

    val characters: LiveData<List<Person>>
        get() = _characters


    private val starWarsRepository: StarWarsRepository = StarWarsRepository()
    private val planetRepository:PlanetRepository =
       PlanetRepository(getDatabase(application).planetDao())
    private var _movies: MutableLiveData<List<Film>> = MutableLiveData()
    private var _isF:MutableLiveData<Boolean> = MutableLiveData()
    private var _characters: MutableLiveData<List<Person>> = MutableLiveData()


    init {
        _isF.value = false
    }

    fun getFilmsFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            _movies.value = starWarsRepository.getFilmsFromUrlList(list)
        }
    }

    fun getPeopleFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            _characters.value=starWarsRepository.getPeopleFromUrlList(list)
        }
    }

    fun isFavourite(name:String){

        viewModelScope.launch{
            _isF.value=planetRepository.isFav(name)
        }


    }
    fun add(planet: Planet){
        var favPlanet = FavPlanet(
        planet.name,
        planet.rotation_period,
        planet.orbital_period,
        planet.diameter,
        planet.climate,
        planet.terrain,
        planet.population

        )
        viewModelScope.launch {
            planetRepository.add(favPlanet)
        }
    }

    fun remove(selectedPlanet: Planet) {
        planetRepository.delete(selectedPlanet.name)
    }


}