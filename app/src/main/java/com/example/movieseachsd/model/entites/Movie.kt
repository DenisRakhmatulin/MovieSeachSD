package com.example.movieseachsd.model.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val movie_id: Int,
    val movie_pic: String,
    val movie_title: String,
    val vote_average: Number
) : Parcelable
