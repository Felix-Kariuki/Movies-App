package com.flexcode.movie.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var results: List<Movie>,

    var movies: MutableList<Movie>
)