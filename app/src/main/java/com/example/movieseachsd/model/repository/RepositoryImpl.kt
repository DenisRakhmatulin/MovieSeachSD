package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.entites.Details

class RepositoryImpl : Repository {
    override fun getDetailsFromServer() = Details()
    override fun getDetailsFromLocalStorage() = Details()
}