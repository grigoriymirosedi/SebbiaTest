package com.example.sebbiatest.data.dto

import com.example.sebbiatest.domain.model.NewsCategory

data class NewsCategoryDTO (
    val id: Int,
    val name: String
)

fun NewsCategoryDTO.toNewsCategory(): NewsCategory {
    return NewsCategory(
        id = id,
        name = name
    )
}



