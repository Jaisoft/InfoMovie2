package com.jaimeg.infomovie.models.db



import android.os.AsyncTask
import com.jaimeg.infomovie.models.MovieRepository
import com.jaimeg.infomovie.models.entities.Movie
import com.jaimeg.infomovie.viewsModels.MovieViewModel


object MoviesPopulateDB {

    fun populateAsync(db: InfoMovieDB, moviesList: MutableList<Movie>, repository: MovieRepository, viewViewModel: MovieViewModel) {
        PopulateDbAsync(db, moviesList, repository, viewViewModel).execute()
    }

    private fun addMovies(db: InfoMovieDB, moviesList: MutableList<Movie>) {
        db.movieDao().insertAll(moviesList)
    }

    private fun getMovies(db: InfoMovieDB): MutableList<Movie> {
        return db.movieDao().getAllMovies()
    }

    private class PopulateDbAsync internal constructor(
            private val db: InfoMovieDB,
            private val moviesList: MutableList<Movie>,
            private val moviesRepository: MovieRepository,
            private val viewViewModel: MovieViewModel) : AsyncTask<Void, Void, MutableList<Movie>>() {

        override fun doInBackground(vararg params: Void): MutableList<Movie> {
            addMovies(db, moviesList)
            return getMovies(db)
        }

        override fun onPostExecute(result: MutableList<Movie>?) {
            super.onPostExecute(result)
        }

    }
}
