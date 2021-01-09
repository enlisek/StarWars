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
//            val response=api.getExPerson().awaitResponse()
//
//            if(response.isSuccessful)
//            {
//                val data=response.body()!!
//                Log.d("rr", "${data?.name ?: "brak nazwy"}")
////                for(v in data.people)
////                {
////                    Log.d("rr", "${v?.name ?: "brak nazwy"}")
////                }
//            }

            //wywołanie wszystkich ludzi z danej strony- nie działa. Mam użyć QUERY zamiast GET, żeby zapytanie mogło byc dynamiczne...?
//            val response=api.getAllPeople(1).awaitResponse()
//
//            if(response.isSuccessful)
//            {
//                val data=response.body()!!
//                for(v in data.results)
//                {
//                    Log.d("rr", "${v?.name ?: "brak nazwy"}")
//                }
//            }


            val response=api.getLeia().awaitResponse()

            if(response.isSuccessful)
            {
                val data=response.body()!!
                Log.d("XXXXXXXXXXXXXXXX", "${data?.name ?: "brak nazwy"}")
            }

        }

    }


}