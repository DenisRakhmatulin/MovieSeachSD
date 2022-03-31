package com.example.movieseachsd.ui.main

import com.example.movieseachsd.model.repository.Repository
import com.example.movieseachsd.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
}