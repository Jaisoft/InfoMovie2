package com.jaimeg.infomovie.models

import com.jaimeg.infomovie.models.api.ImpGetMoviesCallback
import com.jaimeg.infomovie.models.api.ImpNetwork
import com.jaimeg.infomovie.models.db.InfoMovieDB
import com.jaimeg.infomovie.models.db.MoviesPopulateDB
import com.jaimeg.infomovie.models.entities.Movie

class MovieRepository {

    fun addMovieDB(movie: Movie, db: InfoMovieDB): Long{
        return db.movieDao().addMovie(movie)
    }

    fun getMovieById(id: Long, db: InfoMovieDB):Movie {
        return db.movieDao().getMovieById(id)
    }

    fun fetchMoviesApi(callback: ImpGetMoviesCallback, impNetwork: ImpNetwork) {
        impNetwork.fetchMoviesApi(callback)

    }

    fun setMoviesDB(movies: MutableList<Movie>, db: InfoMovieDB) {
        MoviesPopulateDB.populateAsync(db,movies)
    }

    fun getMoviesFromIdentifiers(db: InfoMovieDB, idList: MutableList<Int>): MutableList<Movie> {
        return db.movieDao().getMoviesFromIdentifiers(idList)
    }



    companion object {

        private var INSTANCE: MovieRepository? = null
        @Synchronized
        fun getInstance(): MovieRepository? {

            if (INSTANCE == null) {
                synchronized(MovieRepository::class) {
                    INSTANCE = MovieRepository()
                }

            }
            return INSTANCE
        }
    }
}

