package com.example.movieseachsd.model.entites


data class Details(
    val movie: Movie = getDefaultMovie(),
    val release_date: String = "1999-10-12",
    val overview: String = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
    val genre: String = "Drama",
    val runtime: Int = 139
)

fun getDefaultMovie() = Movie(1, "@drawable/movie_sample_pic","Movie title", 7.7)
