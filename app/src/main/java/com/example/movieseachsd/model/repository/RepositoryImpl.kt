package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.entites.Details
import com.example.movieseachsd.model.entites.getMovies

class RepositoryImpl : Repository {
    override fun getDetailsFromServer() = Details()
    override fun getDetailsFromLocalStorage() = getMovies()
}