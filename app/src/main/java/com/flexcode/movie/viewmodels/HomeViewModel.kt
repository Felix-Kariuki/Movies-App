package com.flexcode.movie.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.flexcode.movie.models.Movie
import com.flexcode.movie.models.MovieResponse
import com.flexcode.movie.data.remote.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val popularMovie = MutableLiveData<List<Movie>>()
    val upcomingMovie = MutableLiveData<List<Movie>>()
    val loading = MutableLiveData<Boolean>()


    fun getPopularMovies(){
        loading.value = true
        disposable.add(
            apiClient.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        popularMovie.value = t.results
                        loading.value = false
                        Log.d("Popular", "Retrieved${popularMovie.value}")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("PopularError", "${e.message} + ${"Cause : "} + ${e.cause}")
                    }
                })
        )
    }

    fun getUpcomingMovies(){
        loading.value = true
        disposable.add(
            apiClient.getUpcomingMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        upcomingMovie.value = t.results
                        loading.value = false
                        Log.i("Upcoming : ", "Upcoming Retrieved")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Upcoming : ", "UPCOMING ERROR${e.message}")
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}