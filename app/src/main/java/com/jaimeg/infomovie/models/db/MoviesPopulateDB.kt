package com.jaimeg.infomovie.models.db



import android.os.AsyncTask
import com.jaimeg.infomovie.models.entities.Movie

object MoviesPopulateDB {

    fun populateAsync(db: InfoMovieDB, moviesList: MutableList<Movie>) {
        PopulateDbAsync(db, moviesList).execute()
    }

    private fun addMovies(db: InfoMovieDB, moviesList: MutableList<Movie>) {
        db.movieDao().insertAll(moviesList)
    }

    private fun getMovies(db: InfoMovieDB): MutableList<Movie> {
        return db.movieDao().getAllMovies()
    }

    private class PopulateDbAsync internal constructor(
            private val db: InfoMovieDB,
            private val moviesList: MutableList<Movie>) : AsyncTask<Void, Void, MutableList<Movie>>() {

        override fun doInBackground(vararg params: Void): MutableList<Movie> {
            addMovies(db, moviesList)
            return getMovies(db)
        }

    }




}
