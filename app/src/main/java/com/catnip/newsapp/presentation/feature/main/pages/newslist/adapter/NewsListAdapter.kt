package com.catnip.newsapp.presentation.feature.main.pages.newslist.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.catnip.newsapp.databinding.ItemNewsBinding
import com.catnip.newsapp.presentation.model.news.News

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class NewsListAdapter(private val itemClick: (News) -> Unit) :
    RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<News>() {
                override fun areItemsTheSame(
                    oldItem: News,
                    newItem: News
                ): Boolean {
                    return oldItem.title == newItem.title
                }

                override fun areContentsTheSame(
                    oldItem: News,
                    newItem: News
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            }
        )

    fun submitData(newList: List<News>) {
        dataDiffer.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bindView(dataDiffer.currentList[position])
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size


    class NewsItemViewHolder(private val binding: ItemNewsBinding, val itemClick: (News) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: News) {
            with(item) {
                binding.tvTitle.text = item.title
                binding.tvNewsAuthor.text = item.author
                itemView.setOnClickListener { itemClick(this) }
            }

        }
    }

}