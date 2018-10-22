package com.jaimeg.infomovie.models.db

import android.os.AsyncTask
import com.jaimeg.infomovie.models.entities.Favorite

object FavoritePopulateDB {


    private fun deleteFavorite(db: InfoMovieDB, favorite: Favorite) {
        db.favoriteDao().delete(favorite)
    }


    private fun addFavorite(db: InfoMovieDB, favorite: Favorite) {
        db.favoriteDao().insert(favorite)
    }

    fun getFavorite(db: InfoMovieDB): MutableList<Favorite> {
        return db.favoriteDao().getAllFavorite()
    }

    fun insertFavoriteAsync(db: InfoMovieDB, favorite: Favorite) {
        InsertFavoriteDBAsyncTask(db, favorite).execute()
    }

    fun deleteFavoriteAsync(db: InfoMovieDB, favorite: Favorite) {
        DeleteFavoriteDBAsyncTask(db, favorite).execute()
    }

    private class InsertFavoriteDBAsyncTask internal constructor(private val db: InfoMovieDB, private val favorite: Favorite) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            addFavorite(db, favorite)
            return null
        }
    }

    private class DeleteFavoriteDBAsyncTask internal constructor(private val db: InfoMovieDB, private val favorite: Favorite) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            deleteFavorite(db, favorite)
            return null
        }
    }
}
