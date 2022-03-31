package com.example.movieseachsd.ui.main

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appmodule = module {
    viewModel { MainViewModel() }
}