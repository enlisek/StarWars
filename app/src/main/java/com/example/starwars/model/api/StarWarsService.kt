package com.example.starwars.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://swapi.dev/api/"

object StarWarsService {
    private val retrofit by lazy{

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api: StarWarsApi by lazy {
        retrofit
            .create(StarWarsApi::class.java)
    }

}