package com.jaimeg.infomovie.models.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jaimeg.infomovie.models.entities.Favorite


@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun  getAllFavorite() : MutableList<Favorite>

    @Insert
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}
