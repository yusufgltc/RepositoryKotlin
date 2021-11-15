package com.edushare.repositorykotlin.repo

import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.edushare.repositorykotlin.data.Post

class Repository(var mRequestQueue: RequestQueue) {

    var posts = MutableLiveData<ArrayList<Post>>()

    fun getDataFromLocal(): ArrayList<Post> {
        var lst = ArrayList<Post>()
        var post1 = Post(1, 1, "Tom", "Test data")
        var post2 = Post(1, 1, "Thomas", "Tuesday")
        var post3 = Post(1, 1, "Tim", "Going to Mars")
        var post4 = Post(1, 1, "Theranos", "Big five animals")
        lst.add(post1)
        lst.add(post2)
        lst.add(post3)
        lst.add(post4)
        return lst
    }

    fun fetchOnlineData() {
        val url = "https://jsonplaceholder.typicode.com/posts"
        var onlineposts = ArrayList<Post>();

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener
            { response -> //retrieved data is stored in this response object
                for (i in 0 until response.length()) {
                    //this for loop iterates through the retrieved arraylist
                    val obj = response.getJSONObject(i)

                    //retrieve specific variables
                    val userId = obj.getInt("userId")
                    val id = obj.getInt("id")
                    val title = obj.getString("title")
                    val body = obj.getString("body")

                    var post = Post(userId, id, title, body)
                    onlineposts.add(post) //adding Post objects to an arraylist

                }
                posts.value = onlineposts //updates the mutablelivedata with the retrieved data

            },
            { error ->
            }
        )
        mRequestQueue.add(jsonArrayRequest)
    }
}