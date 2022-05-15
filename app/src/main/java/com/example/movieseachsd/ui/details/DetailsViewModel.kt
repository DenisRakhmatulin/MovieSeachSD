package com.example.movieseachsd.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieseachsd.model.AppState
import com.example.movieseachsd.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    private val localLiveData: MutableLiveData<AppState> = MutableLiveData()
    val movieLiveData: LiveData<AppState> get() = localLiveData

    fun loadData(id: Int){
        localLiveData.value = AppState.Loading
        Thread{
            val data = repository.getDetailsFromServer(id)
            repository.saveEntity(data)
            localLiveData.postValue(AppState.Success(listOf(data)))
        }.start()

    }
}