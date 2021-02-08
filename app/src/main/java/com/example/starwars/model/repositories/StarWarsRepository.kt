package com.example.starwars.model.repositories

import android.util.Log
import com.example.starwars.model.api.StarWarsService
import com.example.starwars.model.entities.*
import retrofit2.Call
import retrofit2.awaitResponse
import retrofit2.http.GET
import retrofit2.http.Path

class StarWarsRepository {


        suspend fun getPlanetByUrl(fullUrl: String): Planet {
            return StarWarsService.api.getPlanetByUrl(fullUrl).awaitResponse().body()?: Planet("","","","","","","",listOf(),listOf())
        }



        suspend fun getAllPeople(): List<Person> {
            var i=1
            var peopleOnPage = StarWarsService.api.getAllPeopleOnPage(i).awaitResponse().body()!!
            var listOfPeople = mutableListOf<Person>()
            while (peopleOnPage.next!=null)
            {
                for (item in peopleOnPage.results)
                {
                    listOfPeople.add(item)
                }
                i++
                peopleOnPage = StarWarsService.api.getAllPeopleOnPage(i).awaitResponse().body()!!
            }
            for (item in peopleOnPage.results)
            {
                listOfPeople.add(item)
            }
            return listOfPeople
        }

        suspend fun getAllPlanets(): List<Planet> {
            var i=1
            var planetsOnPage = StarWarsService.api.getAllPlanetsOnPage(i).awaitResponse().body()!!
            var listOfPlanets = mutableListOf<Planet>()
            while (planetsOnPage.next!=null)
            {
                for (item in planetsOnPage.results)
                {
                    listOfPlanets.add(item)
                }
                i++
                planetsOnPage = StarWarsService.api.getAllPlanetsOnPage(i).awaitResponse().body()!!
            }
            for (item in planetsOnPage.results)
            {
                listOfPlanets.add(item)
            }
            return listOfPlanets
        }

        suspend fun getAllFilms(): ListOfFilms {
            return StarWarsService.api.getAllFilms().awaitResponse().body()!!
        }

        suspend fun getPeopleFromUrlList(list: List<String>) : List<Person>
        {
            var listOfPeople = mutableListOf<Person>()
            for (item in list)
            {
                listOfPeople.add(StarWarsService.api.getPersonByUrl(item).awaitResponse().body()!!)
            }
            return listOfPeople

        }

        suspend fun getPlanetsFromUrlList(list: List<String>) : List<Planet>
        {
            var listOfPlanets = mutableListOf<Planet>()
            for (item in list)
            {
                listOfPlanets.add(StarWarsService.api.getPlanetByUrl(item).awaitResponse().body()!!)
            }
            return listOfPlanets
        }

        suspend fun getFilmsFromUrlList(list: List<String>) : List<Film>
        {
            var listOfFilms = mutableListOf<Film>()
            for (item in list)
            {
                listOfFilms.add(StarWarsService.api.getFilmByUrl(item).awaitResponse().body()!!)
            }
            return listOfFilms

        }

}