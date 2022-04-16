package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.entites.Details

interface Repository {
    fun getDetailsFromServer(id: Int): Details
    fun getDetailsFromLocalStorage(): List<Details>
}