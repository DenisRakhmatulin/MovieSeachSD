package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.entites.Details
import com.example.movieseachsd.model.entites.Movie
import com.example.movieseachsd.model.entites.getMovies
import com.example.movieseachsd.model.entites.rest_entites.GenresDTO
import com.example.movieseachsd.model.rest.MovieRepo

class RepositoryImpl : Repository {
    override fun getDetailsFromServer(id: Int): Details {
        val dto = MovieRepo.api.getMovie(id.toString()).execute().body()
        //val dto = MovieLoader.loadMovie(id)
        return Details(
            movie = Movie(id, dto?.posterPath, dto?.title, dto?.voteAverage),
            release_date = dto?.releaseDate,
            overview = dto?.overview,
            genre = movieGenresExtracor(dto?.genres),
            runtime = dto?.runtime ?: 0
        )
    }

    override fun getDetailsFromLocalStorage() = getMovies()

    fun movieGenresExtracor(genres: MutableList<GenresDTO>?): String {
        var genreList = ""
        if (genres != null) {
            for (item in genres) {
                val genreItem = item.name
                if (genreItem != null) {
                    if (genreList.equals("")) {
                        genreList = genreItem
                    } else {
                        genreList = "$genreList, $genreItem"
                    }
                }
            }
        }
        return genreList
    }
}