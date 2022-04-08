package com.flexcode.movie.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.flexcode.movie.data.remote.ApiClient
import com.flexcode.movie.models.MovieSearch
import com.flexcode.movie.models.MovieSearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val searchMovies = MutableLiveData<List<MovieSearch>>()

    fun getSearchMovies(search: String) {
        disposable.add(
            apiClient.getSearchMovies(search)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieSearchResponse>() {
                    override fun onSuccess(t: MovieSearchResponse) {
                        searchMovies.value = t.results
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}