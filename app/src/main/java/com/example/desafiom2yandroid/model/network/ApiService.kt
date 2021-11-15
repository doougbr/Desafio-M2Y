package com.example.desafiom2yandroid.model.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/8859?")
    suspend fun getMovieDetails(
        @Query("api_key") key: String
    ): Response<MovieDetails>

    @GET("movie/550/similar?")
    suspend fun getSimilarMovies(
        @Query("api_key") key: String
    ): SimilarMovies
}

