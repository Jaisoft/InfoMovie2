package com.jaimeg.infomovie.viewsModels

import com.jaimeg.infomovie.models.FavoriteRepository
import com.jaimeg.infomovie.models.db.InfoMovieDB
import com.jaimeg.infomovie.models.entities.Favorite



class FavoriteViewModel {


    fun setFavoriteDB(favorite: Favorite, db: InfoMovieDB) {
        FavoriteRepository.getInstance()!!.setFavoriteDB(favorite, db)
    }

    fun getFavoriteDB(db: InfoMovieDB): MutableList<Favorite> {
        return FavoriteRepository.getInstance()!!.getFavoriteDB(db)
    }

    fun deleteFavoriteDB(favorite: Favorite, db: InfoMovieDB) {
        FavoriteRepository.getInstance()!!.deleteFavoriteDB(favorite, db)
    }

    companion object {


        private var INSTANCE: FavoriteViewModel? = null

        @Synchronized
        fun getInstance(): FavoriteViewModel? {

            if (INSTANCE == null) {
                synchronized(FavoriteViewModel::class) {
                    INSTANCE = FavoriteViewModel()
                }

            }
            return INSTANCE
        }
    }


}
