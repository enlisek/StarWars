package com.example.starwars.model.repositories

import android.util.Log
import com.example.starwars.model.api.StarWarsService
import com.example.starwars.model.entities.*
import retrofit2.Call
import retrofit2.awaitResponse
import retrofit2.http.GET
import retrofit2.http.Path

class StarWarsRepository {

    companion object {

        suspend fun getFilmByUrl(fullUrl: String): Film {
            return StarWarsService.api.getFilmByUrl(fullUrl).awaitResponse().body()?: Film("",0,"","","","",listOf(),listOf())
        }

        suspend fun getPersonByUrl(fullUrl: String): Person {
            return StarWarsService.api.getPersonByUrl(fullUrl).awaitResponse().body()?: Person("","","","","","","","","",listOf())
        }

        suspend fun getPlanetByUrl(fullUrl: String): Planet {
            return StarWarsService.api.getPlanetByUrl(fullUrl).awaitResponse().body()?: Planet("",0,0,0,"","","",listOf(),listOf())
        }


        suspend fun getAllPeople(page: Int): ListOfPeople {
            return StarWarsService.api.getAllPeople(page).awaitResponse().body()?: ListOfPeople(listOf(),"")
        }

        suspend fun getAllPlanets(): List<Planet> {
            return StarWarsService.api.getAllPlanets().awaitResponse().body()?: listOf()
        }

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


        suspend fun getCzlek(id_czleka: Int): Person {
            return StarWarsService.api.getCzlek(id_czleka).awaitResponse().body()!!
        }


//        suspend fun getExPerson(): Person {
//            return StarWarsService.api.getExPerson().awaitResponse().body()!!
//        }

        suspend fun getAllFilms(): List<Film> {
            return StarWarsService.api.getAllFilms().awaitResponse().body()?: listOf()
        }

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
}