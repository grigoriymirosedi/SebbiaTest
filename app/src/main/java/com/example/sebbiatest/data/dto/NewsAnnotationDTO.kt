package com.example.sebbiatest.data.dto

import com.example.sebbiatest.domain.model.NewsAnnotation
import java.sql.Date

data class NewsAnnotationDTO(
    val id: Int,
    val title: String,
    val date: Date,
    val shortDescription: String,
)

fun NewsAnnotationDTO.toNewsAnnotation(): NewsAnnotation {
    return NewsAnnotation(
        id = id,
        title = title,
        date = date.toString(),
        shortDescription = shortDescription
    )
}