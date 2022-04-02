package com.flexcode.movie.network

import com.flexcode.movie.models.MovieResponse
import com.flexcode.movie.util.Constants.BASE_URL
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkhttpClient())
        .build()
        .create(ApiService::class.java)


    fun getPopularMovies(): Single<MovieResponse> {
        return api.getPopularMovies()
    }

    fun getUpcomingMovies(): Single<MovieResponse> {
        return api.getUpComingMovies()
    }

    fun getTopRatedMovies(): Single<MovieResponse>{
        return api.getTopRatedMovies()
    }

    private fun getOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor())
        return  client.build()
    }
}