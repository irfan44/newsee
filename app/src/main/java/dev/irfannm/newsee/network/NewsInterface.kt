package dev.irfannm.newsee.network

import dev.irfannm.newsee.data.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    companion object {
        const val API_KEY = "f10abb03319d40f49b4c4f62ee18809e"
    }

    @GET("v2/top-headlines?apiKey=$API_KEY&pageSize=5")
    fun getHeadlines(@Query("country") country : String, @Query("page") page: Int) : Call<News>

    @GET("v2/everything?apiKey=$API_KEY")
    fun getAllNews(@Query("q") query: String, @Query("page") page: Int): Call<News>
}

