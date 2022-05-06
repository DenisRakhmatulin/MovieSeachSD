package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.entites.Details
import com.example.movieseachsd.model.entites.Movie
import com.example.movieseachsd.model.entites.getMovies
import com.example.movieseachsd.model.entites.rest_entites.GenresDTO
import com.example.movieseachsd.model.entites.rest_entites.MovieDTO
import com.example.movieseachsd.model.entites.rest_entites.ResultsDTO
import com.example.movieseachsd.model.entites.rest_entites.SearchDTO
import com.example.movieseachsd.model.rest.MovieRepo
import com.example.movieseachsd.model.rest.SearchRepo

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

    override fun getListFromServer(page: Int, query: String, include_adult: Boolean): List<Details> {
        val dto = SearchRepo.api.getMovieList(page, query, include_adult).execute().body()
        var detailsList : List<Details> = mutableListOf()
        if (dto != null) {
            for(item in dto.results){
                detailsList += Details(Movie(item.id,item.posterPath,item.title,item.voteAverage),item.releaseDate,"","")
            }
        }
        return detailsList
    }

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