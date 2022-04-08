package com.flexcode.movie.models


data class MovieResponse(
    var results: List<Movie>,

    var movies: MutableList<Movie>
)

//video response
data class MovieVideoResponse(
    var id: Int,

    var results: List<MovieVideo>
)

//detail
data class MovieDetailResponse(
    var id: Int,
    var backdrop_path: String?=null,
    var overview: String?=null,
    var poster_path: String?=null,
    var release_date: String?=null,
    var title: String? = null,
    var genres: List<MovieGenre>?=null,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)

data class MovieSearchResponse(
    var results: List<MovieSearch>
)