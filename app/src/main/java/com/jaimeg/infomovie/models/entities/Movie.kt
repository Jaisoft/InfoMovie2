package com.jaimeg.infomovie.models.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * No args constructor for use in serialization
 *
 */
@Entity(tableName = "movie")
class Movie : Serializable {
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null
    @ColumnInfo(name = "imdb_id")
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @ColumnInfo(name = "budget")
    @SerializedName("budget")
    @Expose
    var budget: Int? = null
    @ColumnInfo(name = "homepage")
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    @Expose
    var popularity: Float? = null
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
    @ColumnInfo(name = "revenue")
    @SerializedName("revenue")
    @Expose
    var revenue: Int? = null
    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    @Expose
    var runtime: Int? = null
    @ColumnInfo(name = "status")
    @SerializedName("status")
    @Expose
    var status: String? = null
    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String? = null
    @ColumnInfo(name = "video")
    @SerializedName("video")
    @Expose
    var video: Boolean? = null
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    companion object {
        private const val serialVersionUID = -6969634469946275518L
    }

}