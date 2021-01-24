package com.example.starwars.viewmodel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.Person
import com.example.starwars.model.repositories.StarWarsRepository
import kotlinx.coroutines.launch

class CharacterListViewModel(application: Application):AndroidViewModel(application) {
    private val starWarsRepository: StarWarsRepository
    private var _characters: MutableLiveData<List<Person>> = MutableLiveData()
    val characters: LiveData<List<Person>>
        get() = _characters
    init {
        starWarsRepository = StarWarsRepository()
        updateAllPeople()
    }


    fun updateAllPeople()
    {
        viewModelScope.launch {
            _characters.value=starWarsRepository.getAllPeople()
        }
    }

    fun getPeopleFromUrlList(list: List<String>)
    {
        viewModelScope.launch {
            _characters.value = starWarsRepository.getPeopleFromUrlList(list)
        }
    }

    //fun getFav
}