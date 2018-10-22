package com.jaimeg.infomovie.models.db

import android.arch.persistence.room.*

import com.jaimeg.infomovie.models.entities.Movie


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie where id like :id")
    fun getMovieById(id: Long): Movie

    @Query("SELECT * FROM movie")
    fun getAllMovies(): MutableList<Movie>

    @Query("SELECT * FROM movie WHERE id IN (:idList)")
    fun getMoviesFromIdentifiers(idList: List<Int>): MutableList<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    fun insertAll(movieEntities: MutableList<Movie>)

    @Delete
    fun deleteMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie ): Long

    @Update
    fun updateMovie(movie: Movie)
}


