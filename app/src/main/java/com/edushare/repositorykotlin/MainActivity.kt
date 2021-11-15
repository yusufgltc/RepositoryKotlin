package com.edushare.repositorykotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.edushare.repositorykotlin.viewmodel.MainViewModel
import com.edushare.repositorykotlin.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mRequestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text: TextView = findViewById(R.id.text)
        mRequestQueue = Volley.newRequestQueue(this)
        mainViewModelFactory = MainViewModelFactory(mRequestQueue)
        mainViewModel =
            ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)

        text.text = "Local Data\n" + mainViewModel.localposts.value!!.toString()

        mainViewModel.onlineposts.observeForever {
            if (it.isEmpty()) {
                //do something
            } else {
                text.text = "Online Data\n" + it.toString()
            }
        }
    }
}