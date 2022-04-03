package com.example.movieseachsd.model.repository

import com.example.movieseachsd.model.entites.Details

interface Repository {
    fun getDetailsFromServer(): Details
    fun getDetailsFromLocalStorage(): List<Details>
}