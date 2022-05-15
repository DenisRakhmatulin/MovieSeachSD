package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.database.Database
import com.example.movieseachsd.model.database.HistoryEntity
import com.example.movieseachsd.model.entites.Details
import com.example.movieseachsd.model.entites.Movie
import com.example.movieseachsd.model.entites.getMovies
import com.example.movieseachsd.model.entites.rest_entites.GenresDTO
import com.example.movieseachsd.model.rest.MovieRepo
import com.example.movieseachsd.model.rest.SearchRepo

class RepositoryImpl (private val db: Database): Repository {
    override fun getDetailsFromServer(id: Int): Details {
        val dto = MovieRepo.api.getMovie(id.toString()).execute().body()
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
        dto?.results?.forEach { item ->
            detailsList = detailsList + Details(Movie(item.id,item.posterPath,item.title,item.voteAverage),item.releaseDate,"","")
        }
        return detailsList
    }

    override fun saveEntity(details: Details) {
        db.historyDao().insert(convertDetailsToEntity(details))
    }

    private fun convertDetailsToEntity(details: Details): HistoryEntity {
        return HistoryEntity(
            0, details.movie.movie_title ?: "", details.release_date ?: "",details.runtime ?: 0
        )
    }

    override fun getAllHistory(): List<Details> {
        return convertHistoryEntityToDetails(db.historyDao().all())
    }

    private fun convertHistoryEntityToDetails(entityList: List<HistoryEntity>): List<Details> {
        return entityList.map {
            Details(Movie(0,"",it.movieTitle, 0.0), it.releaseDate, "", "", it.runtime)
        }

    }

    fun movieGenresExtracor(genres: MutableList<GenresDTO>?): String {
        var genreList = ""
        if (genres != null) {
            for (item in genres) {
                val genreItem = item.name
                if (genreItem != null) {
                    genreList = if (genreList == "") {
                        genreItem
                    } else {
                        "$genreList, $genreItem"
                    }
                }
            }
        }
        return genreList
    }


}