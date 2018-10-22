package com.jaimeg.infomovie.viewsModels
import android.arch.lifecycle.ViewModel
import com.jaimeg.infomovie.models.MovieRepository
import com.jaimeg.infomovie.models.api.ImpGetMoviesCallback
import com.jaimeg.infomovie.models.api.ImpNetwork
import com.jaimeg.infomovie.models.db.InfoMovieDB
import com.jaimeg.infomovie.models.entities.Movie


class MovieViewModel: ViewModel() {

    fun addMovieDB(movie: Movie, db: InfoMovieDB, moviesRepository: MovieRepository):Long {
        return moviesRepository.addMovieDB(movie, db)
    }

    fun getMovieById(id: Long, db: InfoMovieDB,moviesRepository: MovieRepository): Movie {
       return moviesRepository.getMovieById(id,db)
    }

    fun fetchMoviesApi(callback: ImpGetMoviesCallback, moviesRepository: MovieRepository, impNetwork: ImpNetwork) {
        moviesRepository.fetchMoviesApi(callback,impNetwork)
    }

    fun setMoviesDB(movies: MutableList<Movie>, db: InfoMovieDB, repository: MovieRepository) {
        repository.setMoviesDB(movies,db)
    }

    fun getMoviesFromIdentifiers(db: InfoMovieDB, idsMoviesList: MutableList<Int>, repository: MovieRepository): MutableList<Movie> {
        return repository.getMoviesFromIdentifiers(db, idsMoviesList)
    }




    companion object {


        private var INSTANCE: MovieViewModel? = null

        @Synchronized
        fun getInstance(): MovieViewModel? {

            if (INSTANCE == null) {
                synchronized(MovieViewModel::class) {
                    INSTANCE = MovieViewModel()
                }

            }
            return INSTANCE
        }
    }


}


