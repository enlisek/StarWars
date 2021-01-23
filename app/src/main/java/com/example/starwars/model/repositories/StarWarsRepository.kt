package com.example.starwars.model.repositories

import android.util.Log
import com.example.starwars.model.api.StarWarsService
import com.example.starwars.model.entities.*
import retrofit2.Call
import retrofit2.awaitResponse
import retrofit2.http.GET
import retrofit2.http.Path

class StarWarsRepository {

        suspend fun getFilmByUrl(fullUrl: String): Film {
            return StarWarsService.api.getFilmByUrl(fullUrl).awaitResponse().body()?: Film("",0,"","","","",listOf(),listOf())
        }

        suspend fun getPersonByUrl(fullUrl: String): Person {
            return StarWarsService.api.getPersonByUrl(fullUrl).awaitResponse().body()?: Person("","","","","","","","","",listOf())
        }

        suspend fun getPlanetByUrl(fullUrl: String): Planet {
            return StarWarsService.api.getPlanetByUrl(fullUrl).awaitResponse().body()?: Planet("","","","","","","",listOf(),listOf())
        }


//        suspend fun getAllPeople(page: Int): ListOfPeople {
//            return StarWarsService.api.getAllPeople(page).awaitResponse().body()!!
//        }

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

//        suspend fun getAllPlanets(): List<Planet> {
//            return StarWarsService.api.getAllPlanets().awaitResponse().body()?: listOf()
//        }

        //region Get By Name
        //Person, Planet, Film by name
        suspend fun getPersonByName(search: String): ListOfPeople {
            return StarWarsService.api.getPersonByName(search).awaitResponse().body()!!
        }

        suspend fun getPlanetByName(search: String): ListOfPlanets {
            return StarWarsService.api.getPlanetByName(search).awaitResponse().body()!!
        }

        suspend fun getFilmByName(search: String): ListOfFilms {
            return StarWarsService.api.getFilmByName(search).awaitResponse().body()!!
        }
        //endregion


        suspend fun getPerson(id_person: Int): Person {
            return StarWarsService.api.getCzlek(id_person).awaitResponse().body()!!
        }


//        suspend fun getExPerson(): Person {
//            return StarWarsService.api.getExPerson().awaitResponse().body()!!
//        }



//        suspend fun getDataFromStation(stationId:Int):List<Data>{
//            val result= mutableListOf<Data>()
//            //wyznaczenie sensorów stacji
//            val sensors:List<Sensor> = StationService.api.getAllStationSensorsById(stationId)
//                .awaitResponse().body()?: listOf()
//            Log.d("set","${sensors[0].toString()}")
//            for(s in sensors)
//            {
//                //val idParam=s.param.idParam
//                val idParam=s.id
//                //wyznaczanie wartości dla tego sensora
//                val data:Data?=StationService.api.getSensorDataById(idParam)
//                    .awaitResponse().body()
//                if(data!=null)
//                {
//                    result.add(data)
//                }
//            }
//
//            for(d in result)
//                Log.d("set","ilosc ${d.key} ${d.toString()}")
//            return result
//
//        }

}