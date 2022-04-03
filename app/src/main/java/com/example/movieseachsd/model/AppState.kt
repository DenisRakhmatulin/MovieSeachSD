package com.example.movieseachsd.model

import com.example.movieseachsd.model.entites.Details

sealed class AppState {
    data class Success(val detailsData: Details) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
