package com.example.movieseachsd.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieseachsd.model.AppState
import com.example.movieseachsd.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel (val repository: Repository) : ViewModel() {
    private val localLiveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState> get() = localLiveData

    fun getDetailsFromLocalSource() = getDataFromLocalSource()

    fun getDataFromLocalSource() {
        localLiveData.value = AppState.Loading
        Thread {
            sleep(1000)
            localLiveData.postValue(AppState.Success(repository.getDetailsFromLocalStorage()))
        }.start()

    }

}