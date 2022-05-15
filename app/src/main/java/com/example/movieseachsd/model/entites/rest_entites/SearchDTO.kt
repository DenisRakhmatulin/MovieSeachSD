package com.example.movieseachsd.model.entites.rest_entites

import com.google.gson.annotations.SerializedName

data class SearchDTO(
    val page: Int?,
    val results: List<ResultsDTO>,
    @SerializedName("total_pages") val totalPages: Int?
)
