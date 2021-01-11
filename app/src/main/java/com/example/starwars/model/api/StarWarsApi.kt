package com.example.starwars.model.api

import com.example.starwars.model.entities.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {
            @GET("people/")
            fun getAllPeople(@Query("page") page :Int): Call<ListOfPeople>

            @GET("{fullUrl}")
            fun getFilmByUrl(@Path("fullUrl",encoded = true) fullUrl: String): Call<Film>

            @GET("{fullUrl}")
            fun getPersonByUrl(@Path("fullUrl",encoded = true) fullUrl: String): Call<Person>

            @GET("{fullUrl}")
            fun getPlanetByUrl(@Path("fullUrl",encoded = true) fullUrl: String): Call<Planet>

            //wyszukiwanie po nazwie
            // ?search=Leia%20Organa
            @GET ("people/")
            fun getPersonByName(@Query("search") search:  String): Call<ListOfPeople>

            @GET ("planets/")
            fun getPlanetByName(@Query("search") search:  String): Call<ListOfPlanets>

            @GET ("films/")
            fun getFilmByName(@Query("search") search:  String): Call<ListOfFilms>

//            @GET("people/1/")
//            fun getExPerson(): Call<Person>


            @GET("people/{id_czleka}")
            fun getCzlek(@Path("id_czleka") id_czleka: Int): Call<Person>

            @GET("planets/")
            fun getAllPlanets(): Call<List<Planet>>

            @GET("films/")
            fun getAllFilms(): Call<List<Film>>

            @GET("films/{episode_id}")
            fun getFilmById(@Path("episode_id") episode_id: Int): Call<Film>
}