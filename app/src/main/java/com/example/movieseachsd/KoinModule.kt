package com.example.movieseachsd.ui.main


import androidx.room.Room
import com.example.movieseachsd.model.database.Database
import com.example.movieseachsd.model.repository.Repository
import com.example.movieseachsd.model.repository.RepositoryImpl
import com.example.movieseachsd.ui.details.DetailsViewModel
import com.example.movieseachsd.ui.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            get(),
            Database::class.java,
            "add_database.db"
        ).build()
    }

    single<Repository> { RepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }

}