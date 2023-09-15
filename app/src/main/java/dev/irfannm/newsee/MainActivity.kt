package dev.irfannm.newsee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.irfannm.newsee.adapter.AllNewsAdapter
import dev.irfannm.newsee.adapter.HeadlineAdapter
import dev.irfannm.newsee.data.News
import dev.irfannm.newsee.network.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var allNewsPage: Int = 1
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        getHeadlines()
        getAllNews()

//        val headlineRecyclerView: RecyclerView = findViewById(R.id.all_news_recycler_view)
//        headlineRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val linearLayoutManager = LinearLayoutManager(this@MainActivity)
//                if (!isLoading)
//                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == allNewsData.size - 2) {
//                        loadAllNewsOnScroll()
//                        isLoading = false
//                    }
//            }
//        })
    }
    private fun getHeadlines() {
        val news = NewsService.newsInterface.getHeadlines("us", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val headlineNews = response.body()
                if (headlineNews !== null) {
                    val headlineAdapter = HeadlineAdapter(this@MainActivity, headlineNews.articles)
                    val recyclerView: RecyclerView = findViewById(R.id.headline_recycler_view)
                    val linearLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.adapter = headlineAdapter
                    recyclerView.layoutManager = linearLayoutManager
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error", "Failed to get headline news data")
            }
        })
    }

    private fun getAllNews() {
        val news = NewsService.newsInterface.getAllNews("bank", allNewsPage++)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val allNews = response.body()
                if (allNews !== null) {
                    val allNewsAdapter = AllNewsAdapter(this@MainActivity, allNews.articles)
                    val recyclerView: RecyclerView = findViewById(R.id.all_news_recycler_view)
                    val linearLayoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = allNewsAdapter
                    recyclerView.layoutManager = linearLayoutManager
                    recyclerView.setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error", "Failed to get news data")
            }

        })
    }

    private fun loadAllNewsOnScroll() {
        getAllNews()
        isLoading = true
    }
}