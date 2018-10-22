package com.jaimeg.infomovie.models.api

import android.content.Context
import android.widget.Toast
import com.jaimeg.infomovie.models.MovieRepository
import com.jaimeg.infomovie.models.entities.Movie
import com.jaimeg.infomovie.models.db.InfoMovieDB
import com.jaimeg.infomovie.viewsModels.MovieViewModel

class ImpGetMoviesCallback(
        private val viewViewModel: MovieViewModel,
        private val context: Context,
        private val db: InfoMovieDB,
        private val repository: MovieRepository) : IGetMoviesCallback {

    override fun onSuccess(movies: MutableList<Movie>) {

        viewViewModel.setMoviesDB(movies, db, repository)
    }

    override fun onError() {
        Toast.makeText(context, "Please check your internet connection.", Toast.LENGTH_SHORT).show()
    }
}
