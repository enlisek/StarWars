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
    private val starWarsRepository: StarWarsRepository
    private var _homeworld: MutableLiveData<Planet> = MutableLiveData()
    private val personRepository:PersonRepository =
        PersonRepository(StarWarsDataBase.getDatabase(application).personDao())
    private var _isF:MutableLiveData<Boolean> = MutableLiveData()
    init {
        starWarsRepository = StarWarsRepository()
        _isF.value = false
    }
    val homeworld: LiveData<Planet>
        get() = _homeworld


    val isF:LiveData<Boolean>
        get() =_isF
    private var _movies: MutableLiveData<List<Film>> =MutableLiveData()
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
    fun isFavourite(name:String){
//        viewModelScope.launch {
//            var response = personRepository.isFav(name)
//           delay(2000)
//            _isF.value = response.value
//            Log.d("blanla", isF.toString())
//        }
        viewModelScope.launch{
            _isF.value=personRepository.isFav(name)
        }


    }
    fun add(person:Person){
//        var listOfMovies = mutableListOf<String>()
//        for (item in movies.value!!){
//            listOfMovies.add(item.title)
//        }
        var favPerson = FavPerson(
            person.name,
            person.height,
            person.mass,
            person.hair_color,
            person.skin_color,
            person.eye_color,
            person.birth_year,
            person.gender,
            homeworld.value!!.name //listOfMovies
            )
        viewModelScope.launch{
            personRepository.add(favPerson)
        }


    }
    fun remove(person:Person){
        personRepository.delete(person.name)
    }


}