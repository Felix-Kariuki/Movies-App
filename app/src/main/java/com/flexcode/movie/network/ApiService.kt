package com.flexcode.movie.network

import com.flexcode.movie.models.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies():Single<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(): Single<MovieResponse>
}