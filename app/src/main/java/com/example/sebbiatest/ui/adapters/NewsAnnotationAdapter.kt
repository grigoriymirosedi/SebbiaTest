package com.example.sebbiatest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sebbiatest.R
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.ui.viewmodels.NewsAnnotationViewModel
import org.w3c.dom.Text

class NewsAnnotationAdapter() :
    PagingDataAdapter<NewsAnnotation, NewsAnnotationAdapter.NewsAnnotationViewHolder>(
        differCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAnnotationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_annotation_item, parent, false)
        return NewsAnnotationViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NewsAnnotationViewHolder, position: Int) {
        val newsAnnotationItem = getItem(position)!!
        viewHolder.apply {
            newsAnnotationTitle.text = newsAnnotationItem.title
            newsAnnotationShortDescription.text = newsAnnotationItem.shortDescription
            newsAnnotationDate.text = newsAnnotationItem.date.toString()
        }
        viewHolder.setIsRecyclable(false)
    }

    class NewsAnnotationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var newsAnnotationTitle = view.findViewById<TextView>(R.id.newsAnnotationItemTitleTV)
        var newsAnnotationShortDescription = view.findViewById<TextView>(R.id.newsAnnotationItemShortDescriptionTV)
        var newsAnnotationDate = view.findViewById<TextView>(R.id.newsAnnotationItemDateTV)
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<NewsAnnotation>() {
            override fun areItemsTheSame(
                oldItem: NewsAnnotation,
                newItem: NewsAnnotation,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NewsAnnotation,
                newItem: NewsAnnotation,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}