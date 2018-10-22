package com.jaimeg.infomovie.models.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImpNetwork(private val iNetwork: INetwork) {


    fun fetchMoviesApi(callback: IGetMoviesCallback) {

        iNetwork.getPopularMovies("0b192839ea5e7b349063a918743a66f3", LANGUAGE, 1)
                .enqueue(ImpCallback(callback))
    }



    companion object {

        private val BASE_URL = "https://api.themoviedb.org/3/"
        private val LANGUAGE = "en-US"
        private var INSTANCE: ImpNetwork? = null

        @Synchronized
        fun getInstance(): ImpNetwork?{
            if (INSTANCE == null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                INSTANCE = ImpNetwork(retrofit.create(INetwork::class.java))
            }
            return INSTANCE
        }


    }
}