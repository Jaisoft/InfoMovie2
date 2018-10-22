package com.jaimeg.infomovie.models.api

import com.jaimeg.infomovie.models.entities.MoviesResponse
import com.jaimeg.infomovie.models.db.InfoMovieDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImpCallback(private val callback: IGetMoviesCallback) : Callback<MoviesResponse> {


    override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
        if (response.isSuccessful) {
            val moviesResponse = response.body()
            if (moviesResponse?.movies != null) {
                callback.onSuccess(moviesResponse.movies!!)
            } else {
                callback.onError()
            }
        }
    }

    private val db: InfoMovieDB? = null




    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
        callback.onError()
    }
}
