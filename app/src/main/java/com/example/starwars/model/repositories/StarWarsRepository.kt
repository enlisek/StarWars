package com.example.starwars.model.repositories

import android.util.Log
import com.example.starwars.model.api.StarWarsService
import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.ListOfPeople
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import retrofit2.Call
import retrofit2.awaitResponse
import retrofit2.http.GET

class StarWarsRepository {

    companion object {

        suspend fun getAllPeople(page: Int): ListOfPeople {
            return StarWarsService.api.getAllPeople(page).awaitResponse().body()?: ListOfPeople(listOf())
        }

        suspend fun getAllPlanets(): List<Planet> {
            return StarWarsService.api.getAllPlanets().awaitResponse().body()?: listOf()
        }

        suspend fun getLeia(): Person {
            return StarWarsService.api.getLeia().awaitResponse().body()!!
        }


        suspend fun getExPerson(): Person {
            return StarWarsService.api.getExPerson().awaitResponse().body()!!
        }

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