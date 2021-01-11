package com.example.starwars.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.starwars.R
import com.example.starwars.model.api.StarWarsApi
import com.example.starwars.model.repositories.StarWarsRepository.Companion.getAllPeople
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val   BASE_URL="https://swapi.dev/api/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getStationInfo()
    }



    private fun getStationInfo(){


        val api= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)


        GlobalScope.launch(Dispatchers.IO) {

            //wywołanie pierwszej osoby- działa
//            val response=api.getCzlek(2).awaitResponse()
//
//            if(response.isSuccessful)
//            {
//                Log.d("XXXXXXXXXXXXXXX","YYYYYYYYYYYY")
//                val data=response.body()!!
//                Log.d("rr", "${data?.name ?: "brak nazwy"}")
////                for(v in data.people)
////                {
////                    Log.d("rr", "${v?.name ?: "brak nazwy"}")
////                }
//            }

            //wywołanie listy osob z danej strony- dziala
//            val response=api.getAllPeople(2).awaitResponse()
//            if(response.isSuccessful)
//            {
//                Log.d(":)))))))))))))","YYYYYYYYYYYY")
//                val data=response.body()!!
//                for(v in data.results)
//                {
//                    Log.d("rr", "${v ?: "ni ma"}")
//                }
//            }
//            else{
//                Log.d(":(((((((((((","XXXXXX")
//            }

//              wywołanie Lei- działa
//            val response=api.getPersonByName("Luke").awaitResponse()
//
//            if(response.isSuccessful)
//            {
//                val data=response.body()!!
//                Log.d("XXXXXXXXXXXXXXXX", "${data?.results ?: "brak nazwy"}")
//            }

            //wywolanie filmu po url
            val response=api.getFilmByUrl("https://swapi.dev/api/films/6/").awaitResponse()

            if(response.isSuccessful)
            {
                val data=response.body()!!
                Log.d("XXXXXXXXXXXXXXXX", "${data?.title ?: "brak nazwy"}")
            }

        }

    }


}