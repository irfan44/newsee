package dev.irfannm.newsee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.irfannm.newsee.adapter.HeadlineAdapter
import dev.irfannm.newsee.data.News
import dev.irfannm.newsee.network.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var headlinePage: Int = 1
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHeadlines()

//        val headlineRecyclerView: RecyclerView = findViewById(R.id.headline_recycler_view)
//        headlineRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isLoading) {
//                    loadHeadlineOnScroll()
//                }
//            }
//        })
    }
    private fun getHeadlines() {
        val news = NewsService.newsInterface.getHeadlines("us", headlinePage++)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val headlineNews = response.body()
                Log.d("API Data", response.body().toString())
                if (headlineNews !== null) {
                    val headlineAdapter = HeadlineAdapter(this@MainActivity, headlineNews.articles)
                    val recyclerView: RecyclerView = findViewById(R.id.headline_recycler_view)
                    val linearLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.adapter = headlineAdapter
                    recyclerView.layoutManager = linearLayoutManager
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error", "Failed to get news data")
            }
        })
    }

    private fun loadHeadlineOnScroll() {
        isLoading = true
        getHeadlines()
        isLoading = false
    }
}