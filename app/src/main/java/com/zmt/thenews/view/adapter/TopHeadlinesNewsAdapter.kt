package com.zmt.thenews.view.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zmt.thenews.R
import com.zmt.thenews.databinding.ItemTopHeadlinesNewsBinding
import com.zmt.thenews.helper.DateUtils.msToTimeAgo
import com.zmt.thenews.helper.loadFromUrl
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*


class TopHeadlinesNewsAdapter(private val event: ClickEvent) :
    ListAdapter<TopHeadlinesNews, TopHeadlinesNewsViewHolder>(
        object : DiffUtil.ItemCallback<TopHeadlinesNews>() {
            override fun areItemsTheSame(
                oldItem: TopHeadlinesNews,
                newItem: TopHeadlinesNews
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: TopHeadlinesNews,
                newItem: TopHeadlinesNews
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    var list: MutableList<TopHeadlinesNews> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlinesNewsViewHolder {
        val binding =
            ItemTopHeadlinesNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopHeadlinesNewsViewHolder(binding.root, event)
    }

    override fun onBindViewHolder(holder: TopHeadlinesNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class TopHeadlinesNewsViewHolder(val view: View, private val event: ClickEvent) :
    RecyclerView.ViewHolder(view) {
    private var currentItem: TopHeadlinesNews? = null
    private val binding = ItemTopHeadlinesNewsBinding.bind(view)

    init {
        binding.root.setOnClickListener {
            currentItem?.apply {
                event.itemClick(this)
            }
        }

        binding.ivFav.setOnClickListener {
            currentItem?.apply {
                event.favClick(this)
            }
        }
    }

    fun bind(item: TopHeadlinesNews) {
        currentItem = item
        binding.tvTitle.text = item.title
        binding.ivImage.loadFromUrl(item.urlToImage)
        binding.tvDate.text = item.publishedAt.split("T")[0]
        //finalResult.time.msToTimeAgo(itemView.context)//item.publishedAt.getMilliseconds(ISO_DATE).toString()
        updateIsSaved(item.isSaved)
    }

    fun updateIsSaved(isSaved: Boolean?) {
        if (isSaved == true) {
            binding.ivFav.imageTintList =
                ColorStateList.valueOf(itemView.context.getColor(R.color.purple_700))
        } else {
            binding.ivFav.imageTintList =
                ColorStateList.valueOf(itemView.context.getColor(android.R.color.darker_gray))
        }
    }
}

interface ClickEvent {
    fun itemClick(item: TopHeadlinesNews)

    fun favClick(item: TopHeadlinesNews)
}