package com.jaimeg.infomovie.models.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favorite")
class Favorite {

    @PrimaryKey
    @ColumnInfo(name = "favorite_id")
    var favoriteMovie: Int? = null
}
