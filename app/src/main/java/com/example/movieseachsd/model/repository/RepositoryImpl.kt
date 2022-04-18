package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.MovieLoader
import com.example.movieseachsd.model.entites.Details
import com.example.movieseachsd.model.entites.Movie
import com.example.movieseachsd.model.entites.getMovies

class RepositoryImpl : Repository {
    override fun getDetailsFromServer(id: Int): Details {
        val dto = MovieLoader.loadMovie(id)
        return Details(
            movie = Movie(id, "@drawable/movie_sample_pic", dto?.title, dto?.voteAverage),
            release_date = dto?.releaseDate,
            overview = dto?.overview,
            genre = dto?.genres?.toString(),
            runtime = dto?.runtime ?: 0
        )
    }

    override fun getDetailsFromLocalStorage() = getMovies()
}