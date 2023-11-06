package com.example.sebbiatest.data.dto

import com.example.sebbiatest.domain.model.NewsDetails

data class NewsDetailsDTO(
    val id: Int,
    val title: String,
    val date: String,
    val shortDescription: String,
    val fullDescription: String
)

fun NewsDetailsDTO.toNewsDetails(): NewsDetails {
    return NewsDetails(
        id = id,
        title = title,
        date = date,
        shortDescription = shortDescription,
        fullDescription = fullDescription
    )
}
