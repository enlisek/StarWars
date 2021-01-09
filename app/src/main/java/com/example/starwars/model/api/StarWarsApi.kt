package com.example.starwars.model.api

import com.example.starwars.model.entities.Film
import com.example.starwars.model.entities.ListOfPeople
import com.example.starwars.model.entities.Person
import com.example.starwars.model.entities.Planet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {
    @GET("people/?page={page}")
    fun getAllPeople(@Path("page") page :Int): Call<ListOfPeople>

    @GET("/api/people/?search=Leia%20Organa") //tutaj pewnie znowu trzeba zrobic nowa klase-> FoundPerson, która bedzie miała pole results będące List<Person>
    fun getLeia(): Call<Person>

    @GET("people/1/")
    fun getExPerson(): Call<Person>

    @GET("planets/")
    fun getAllPlanets(): Call<List<Planet>>

    @GET("films/")
    fun getAllFilms(): Call<List<Film>>

    @GET("films/{episode_id}")
    fun getFilmById(@Path("episode_id") episode_id :Int): Call<Film>
}