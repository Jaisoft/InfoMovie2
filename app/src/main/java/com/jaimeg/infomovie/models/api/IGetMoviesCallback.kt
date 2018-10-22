package com.jaimeg.infomovie.models.api

import com.jaimeg.infomovie.models.entities.Movie

interface IGetMoviesCallback {

        fun onSuccess(movies: MutableList<Movie>)

        fun onError()
    }
