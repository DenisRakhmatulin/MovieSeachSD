package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.entites.Details
import com.example.movieseachsd.model.entites.rest_entites.ResultsDTO

interface Repository {
    fun getDetailsFromServer(id: Int): Details
    fun getDetailsFromLocalStorage(): List<Details>
    fun getListFromServer(page: Int, query: String, include_adult: Boolean): List<Details>
    fun saveEntity(details: Details)
    fun getAllHistory() : List<Details>
}