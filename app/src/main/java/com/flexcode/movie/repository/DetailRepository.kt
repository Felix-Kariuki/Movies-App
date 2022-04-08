package com.flexcode.movie.repository


import androidx.lifecycle.LiveData
import com.flexcode.movie.data.local.MovieDao
import com.flexcode.movie.models.Movie

class DetailRepository(private val movieDao: MovieDao) {

    val getAllMovies: LiveData<List<Movie>> = movieDao.getAllMovies()

    fun getSingleMovie(movieId: Int): LiveData<Movie> {
        return movieDao.getSingleMovie(movieId)
    }

    fun insertMovie(movie: Movie){
        movieDao.insertMovie(movie)
    }

    fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }

}