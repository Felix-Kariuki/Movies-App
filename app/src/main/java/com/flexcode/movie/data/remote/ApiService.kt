package com.flexcode.movie.data.remote

import com.flexcode.movie.models.MovieDetailResponse
import com.flexcode.movie.models.MovieResponse
import com.flexcode.movie.models.MovieVideoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies():Single<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(): Single<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int): Single<MovieDetailResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") movieId: Int): Single<MovieVideoResponse>


}