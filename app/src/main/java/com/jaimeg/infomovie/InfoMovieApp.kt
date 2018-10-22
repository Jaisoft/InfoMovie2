package com.jaimeg.infomovie

import android.app.Application
import com.jaimeg.infomovie.models.MovieRepository
import com.jaimeg.infomovie.models.api.ImpNetwork
import com.jaimeg.infomovie.models.db.InfoMovieDB
import com.jaimeg.infomovie.viewsModels.FavoriteViewModel
import com.jaimeg.infomovie.viewsModels.MovieViewModel

class InfoMovieApp : Application() {

    companion object {
        lateinit var database: InfoMovieDB
        lateinit var movieViewModel: MovieViewModel
        lateinit var movieRepo: MovieRepository
        lateinit var impNetwork: ImpNetwork
        lateinit var favoriteViewModel: FavoriteViewModel
    }

    override fun onCreate() {
        super.onCreate()

        InfoMovieApp.database = InfoMovieDB.getInstance(this)!!
        InfoMovieApp.movieViewModel = MovieViewModel.getInstance()!!
        InfoMovieApp.movieRepo = MovieRepository.getInstance()!!
        InfoMovieApp.impNetwork = ImpNetwork.getInstance()!!
        InfoMovieApp.favoriteViewModel = FavoriteViewModel.getInstance()!!
    }

}