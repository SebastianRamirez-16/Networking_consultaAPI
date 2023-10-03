package com.example.proyecto_2.service

import com.example.proyecto_2.response.SuperHeroDataResponse
import com.example.proyecto_2.response.SuperHeroDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/3596346837357491/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName:String): Response<SuperHeroDataResponse>

    @GET("api/3596346837357491/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId:String): Response<SuperHeroDetailResponse>
}