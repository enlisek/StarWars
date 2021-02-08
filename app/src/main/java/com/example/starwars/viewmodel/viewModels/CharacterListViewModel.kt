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
    private val starWarsRepository: StarWarsRepository = StarWarsRepository()
    private var _characters: MutableLiveData<List<Person>> = MutableLiveData()
    val characters: LiveData<List<Person>>
        get() = _characters

    init {
        updateAllPeople()
    }


    fun updateAllPeople()
    {
        viewModelScope.launch {
            _characters.value=starWarsRepository.getAllPeople()
        }
    }


}