package com.flexcode.movie.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.flexcode.movie.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getSingleMovie(movieId:Int): LiveData<Movie>


}