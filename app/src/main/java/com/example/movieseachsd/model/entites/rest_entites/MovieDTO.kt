package com.example.movieseachsd.model.entites.rest_entites

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val genres: MutableList<GenresDTO>?,
    val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    val runtime: Int?,
    val title: String?,
    @SerializedName("vote_average") val voteAverage: Number?

)
