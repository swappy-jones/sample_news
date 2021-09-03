package com.comnet.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comnet.test.NewsAdapter.NewsViewHolder
import com.comnet.test.databinding.ItemNewsBinding

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {
    lateinit var binding: ItemNewsBinding
    private lateinit var news:List<News>
    private lateinit var itemClick:ItemClick

    fun setItemClick(itemClick: ItemClick){
        this.itemClick=itemClick
    }

    fun setNewsData(news:List<News>){
        this.news=news;
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View?) {
            itemClick.onItemClick(news[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun setData(news: News) {
            with(binding) {
                txtTitle.text= news.title
                txtPublishedDate.text= news.publishedDate
                Glide.with(itemView.context).load(news.thumbnail).into(imgNews)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if(::news.isInitialized){
            holder.setData(news[position])
        }
    }

    override fun getItemCount(): Int {
        return if(::news.isInitialized){
            news.size
        }else{
            0
        }
    }

    public interface ItemClick{
        fun onItemClick(news:News)
    }
}