package com.flexcode.movie.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flexcode.movie.data.local.MovieDatabase
import com.flexcode.movie.data.remote.ApiClient
import com.flexcode.movie.models.*
import com.flexcode.movie.repository.DetailRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DetailRepository

    init {
        val dao = MovieDatabase.getInstance(application).movieDao()
        repository = DetailRepository(dao)
    }

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val movieDetails = MutableLiveData<MovieDetailResponse>()
    val movieTrailers = MutableLiveData<List<MovieVideo>>()
    val loading = MutableLiveData<Boolean>()


    fun getMovieDetails(movieId: Int){
        loading.value = true
        disposable.add(
            apiClient.getMovieDetails(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieDetailResponse>(){
                    override fun onSuccess(t: MovieDetailResponse) {

                        movieDetails.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.d("DetailError","${e.message}")
                    }

                })
        )
    }

    fun getTrailers(movieId: Int){
        loading.value = true
        disposable.add(
            apiClient.getMovieTrailers(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieVideoResponse>(){
                    override fun onSuccess(t: MovieVideoResponse) {

                        movieTrailers.value = t.results
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TrailersError","${e.message}")
                    }

                })
        )
    }



    fun insertMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertMovie(movie)
    }
    fun deleteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMovie(movie)}
    fun getSingleMovie(movieId:Int) : LiveData<Movie> =
        repository.getSingleMovie(movieId)
    fun getAllMovies(): LiveData<List<Movie>> =
        repository.getAllMovies

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}