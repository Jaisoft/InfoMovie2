package com.jaimeg.infomovie.models.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jaimeg.infomovie.models.entities.Favorite
import com.jaimeg.infomovie.models.entities.Movie

@Database(entities = arrayOf(Movie::class, Favorite::class), version = 2, exportSchema = false)
abstract class InfoMovieDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun favoriteDao(): FavoriteDao


    companion object {
        private var INSTANCE: InfoMovieDB? = null

        fun getInstance(context: Context): InfoMovieDB? {
            if (INSTANCE == null) {
                synchronized(InfoMovieDB::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InfoMovieDB::class.java, "infomovie_db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}