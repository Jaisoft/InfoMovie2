package com.jaimeg.infomovie.models.api
import com.jaimeg.infomovie.models.entities.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetwork {

    @GET("movie/popular")
        fun getPopularMovies(
                @Query("api_key") apiKey: String,
                @Query("language") language: String,
                @Query("page") page: Int
        ): Call<MoviesResponse>
    }
