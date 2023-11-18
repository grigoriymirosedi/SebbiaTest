package com.example.sebbiatest.ui.adapters

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sebbiatest.R
import com.example.sebbiatest.databinding.NewsAnnotationItemBinding
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.ui.fragments.NewsAnnotationFragmentDirections
import com.example.sebbiatest.ui.viewmodels.NewsAnnotationViewModel
import org.w3c.dom.Text

class NewsAnnotationAdapter() :
    PagingDataAdapter<NewsAnnotation, NewsAnnotationAdapter.NewsAnnotationViewHolder>(
        differCallback
    ) {
    var onItemClick: ((NewsAnnotation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAnnotationViewHolder {
        val binding = NewsAnnotationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsAnnotationViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: NewsAnnotationViewHolder, position: Int) {
        val newsAnnotationItem = getItem(position)!!
        viewHolder.apply {
            binding.apply {
                newsAnnotationItemTitleTV.text = newsAnnotationItem.title
                newsAnnotationItemShortDescriptionTV.text = newsAnnotationItem.shortDescription
                newsAnnotationItemDateTV.text = newsAnnotationItem.date
            }
        }
        viewHolder.setIsRecyclable(false)
        viewHolder.itemView.setOnClickListener {view ->
            val action = NewsAnnotationFragmentDirections.actionNewsAnnotationFragmentToNewsDetailsFragment(
                newsAnnotationItem.id,
                newsAnnotationItem.title,
                newsAnnotationItem.shortDescription,
                newsAnnotationItem.date
            )
            view.findNavController().navigate(action)
        }
    }

    inner class NewsAnnotationViewHolder(val binding: NewsAnnotationItemBinding) : RecyclerView.ViewHolder(binding.root)

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