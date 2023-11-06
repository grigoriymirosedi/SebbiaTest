package com.example.sebbiatest.data.dto

import com.example.sebbiatest.domain.model.NewsAnnotation

data class NewsAnnotationDTO(
    val id: Int,
    val title: String,
    val date: String,
    val shortDescription: String,
)

fun NewsAnnotationDTO.toNewsAnnotation(): NewsAnnotation {
    return NewsAnnotation(
        id = id,
        title = title,
        date = date,
        shortDescription = shortDescription
    )
}