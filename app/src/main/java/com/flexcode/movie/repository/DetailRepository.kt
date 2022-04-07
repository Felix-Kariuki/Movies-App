package com.flexcode.movie.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.flexcode.movie.data.local.MovieDao
import com.flexcode.movie.data.local.MovieDatabase
import com.flexcode.movie.models.Movie

class DetailRepository(private val movieDao: MovieDao) {

    //private val database by lazy { MovieDatabase.getInstance(context) }
    //private val dao by lazy { database.movieDao() }

    val getAllMovies: LiveData<List<Movie>> = movieDao.getAllMovies()
    //val singleMovie: LiveData<Movie> = movieDao.getSingleMovie(movieId)

    fun getSingleMovie(movieId: Int): LiveData<Movie> {
        return movieDao.getSingleMovie(movieId)
    }

    fun insertMovie(movie: Movie){
        movieDao.insertMovie(movie)
    }

    fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }

    private class InsertAsyncTask(val dao: MovieDao): AsyncTask<Movie, Void, Void>(){
        override fun doInBackground(vararg params: Movie): Void? {
            dao.insertMovie(params[0])
            return null
        }
    }

    private class DeleteAsyncTask(val dao: MovieDao): AsyncTask<Movie, Void, Void>(){
        override fun doInBackground(vararg params: Movie): Void? {
            dao.deleteMovie(params[0])
            return null
        }
    }
}