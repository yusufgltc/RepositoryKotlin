package com.edushare.repositorykotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.edushare.repositorykotlin.data.Post
import com.edushare.repositorykotlin.repo.Repository

class MainViewModel(var mRequestQueue: RequestQueue) : ViewModel() {

    var localposts = MutableLiveData<ArrayList<Post>>()
    var onlineposts = MutableLiveData<ArrayList<Post>>()
    var mainRepository = Repository(mRequestQueue)

    init {
        localposts.value = mainRepository.getDataFromLocal()  //fetching local data
        mainRepository.fetchOnlineData() //fetching online data
        onlineposts = mainRepository.posts //updating the online posts array with new data
    }
}