package com.flexcode.movie.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey
    @SerializedName("id")
    var movieId: Int,
    var poster_path: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var original_title: String? = null,
    var original_language: String? = null,
    var title: String? = null,
    var backdrop_path: String? = null,
    var popularity: Double,
    var vote_count: Int,
    var video: Boolean,
    var vote_average: Double
) : Serializable

//moview video
data class MovieVideo(
    var id: String,
    var key: String,
    var name: String,
    var site: String

):Serializable

//genre
data class MovieGenre (
    var genresId: Int,

    var name: String?
) : Serializable

//search
data class MovieSearch(
    var vote_count : Int,
    var poster_path : String?=null,
    var backdrop_path : String?=null,
    var title : String?=null,
    var overview : String?=null,
    var vote_average : String?=null,
    var release_date : String?=null

) : Serializable

