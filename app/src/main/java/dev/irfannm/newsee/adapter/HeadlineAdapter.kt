package dev.irfannm.newsee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.irfannm.newsee.R
import dev.irfannm.newsee.data.Article
import dev.irfannm.newsee.util.FormatDate

class HeadlineAdapter(var context: Context?, private val articles: List<Article>) : RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleText: TextView
        val sourceText: TextView
        val publishDateText: TextView
        val newsImage: ImageView

        init {
            titleText = itemView.findViewById(R.id.titleText)
            sourceText = itemView.findViewById(R.id.sourceText)
            publishDateText = itemView.findViewById(R.id.publishedAtText)
            newsImage = itemView.findViewById(R.id.newsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.headline_news_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleText.text = articles[holder.adapterPosition].title
        holder.sourceText.text = articles[holder.adapterPosition].source.name
        val publishedDate = FormatDate().formatDate(articles[holder.adapterPosition].publishedAt)
        holder.publishDateText.text = publishedDate
        Glide.with(context!!).load(articles[holder.adapterPosition].urlToImage).override(310, 110).into(holder.newsImage)
    }
}