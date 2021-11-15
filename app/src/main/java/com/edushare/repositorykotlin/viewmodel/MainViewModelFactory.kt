package com.edushare.repositorykotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue

class MainViewModelFactory(var mRequestQueue: RequestQueue) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mRequestQueue) as T
        }
        throw IllegalArgumentException("Unknown class")

    }

}