package com.example.sebbiatest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sebbiatest.R
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.ui.viewmodels.NewsAnnotationViewModel
import org.w3c.dom.Text

class NewsAnnotationAdapter(private val newsList: List<NewsAnnotation>): RecyclerView.Adapter<NewsAnnotationAdapter.NewsAnnotationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAnnotationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_annotation_item, parent, false)
        return NewsAnnotationViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NewsAnnotationViewHolder, position: Int) {
        val newsAnnotationItem = newsList[position]
        viewHolder.apply {
            newsAnnotationTitle.text = newsAnnotationItem.title
            newsAnnotationShortDescription.text = newsAnnotationItem.shortDescription
            newsAnnotationDate.text = newsAnnotationItem.date.toString()
        }
    }

    override fun getItemCount(): Int = newsList.size

    class NewsAnnotationViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var newsAnnotationTitle = view.findViewById<TextView>(R.id.newsAnnotationItemTitleTV)
        var newsAnnotationShortDescription = view.findViewById<TextView>(R.id.newsAnnotationItemShortDescriptionTV)
        var newsAnnotationDate = view.findViewById<TextView>(R.id.newsAnnotationItemDateTV)
    }
}