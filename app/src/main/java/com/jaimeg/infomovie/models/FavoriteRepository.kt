package com.jaimeg.infomovie.models

import com.jaimeg.infomovie.models.db.InfoMovieDB
import com.jaimeg.infomovie.models.entities.Favorite
import com.jaimeg.infomovie.models.db.FavoritePopulateDB

class FavoriteRepository private constructor() {

    fun setFavoriteDB(favorite: Favorite, db: InfoMovieDB) {
        FavoritePopulateDB.insertFavoriteAsync(db, favorite)
    }

    fun getFavoriteDB(db: InfoMovieDB): MutableList<Favorite> {
        return FavoritePopulateDB.getFavorite(db)
    }

    fun deleteFavoriteDB(favorite: Favorite, db: InfoMovieDB) {
        FavoritePopulateDB.deleteFavoriteAsync(db, favorite)
    }

    companion object {


        private var INSTANCE: FavoriteRepository? = null

        @Synchronized
        fun getInstance(): FavoriteRepository? {

            if (INSTANCE == null) {
                synchronized(FavoriteRepository::class) {
                    INSTANCE = FavoriteRepository()
                }

            }
            return INSTANCE
        }
    }
}
