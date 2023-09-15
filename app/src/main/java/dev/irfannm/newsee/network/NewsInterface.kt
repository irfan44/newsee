package dev.irfannm.newsee.network

import dev.irfannm.newsee.data.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    companion object {
        const val API_KEY = "4c1d61b4d4ee439c996c02b00e98066e"
    }

    @GET("v2/top-headlines?apiKey=$API_KEY&pageSize=50")
    fun getHeadlines(@Query("country") country : String, @Query("page") page: Int) : Call<News>

    @GET("v2/everything?apiKey=$API_KEY")
    fun getAllNews(@Query("q") query: String, @Query("page") page: Int): Call<News>
}

