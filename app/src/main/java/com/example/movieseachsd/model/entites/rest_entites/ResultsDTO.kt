package com.example.movieseachsd.model.entites.rest_entites

import com.google.gson.annotations.SerializedName

data class ResultsDTO(
    val id: Int?,
    val title: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Number?
)
