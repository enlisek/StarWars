package com.example.starwars.model.api

import com.example.starwars.model.entities.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {
            @GET("people/")
            fun getAllPeopleOnPage(@Query("page") page :Int): Call<ListOfPeople>

            @GET("films/")
            fun getAllFilms(): Call<ListOfFilms>

            @GET("planets/")
            fun getAllPlanetsOnPage(@Query("page") page :Int): Call<ListOfPlanets>



//            search by url

            @GET("{fullUrl}")
            fun getFilmByUrl(@Path("fullUrl",encoded = true) fullUrl: String): Call<Film>

            @GET("{fullUrl}")
            fun getPersonByUrl(@Path("fullUrl",encoded = true) fullUrl: String): Call<Person>

            @GET("{fullUrl}")
            fun getPlanetByUrl(@Path("fullUrl",encoded = true) fullUrl: String): Call<Planet>
//          
//          search by name

            @GET ("people/")
            fun getPersonByName(@Query("search") search:  String): Call<ListOfPeople>

            @GET ("planets/")
            fun getPlanetByName(@Query("search") search:  String): Call<ListOfPlanets>

            @GET ("films/")
            fun getFilmByName(@Query("search") search:  String): Call<ListOfFilms>



            @GET("people/{id_czleka}")
            fun getCzlek(@Path("id_czleka") id_czleka: Int): Call<Person>





            @GET("films/{episode_id}")
            fun getFilmById(@Path("episode_id") episode_id: Int): Call<Film>
}