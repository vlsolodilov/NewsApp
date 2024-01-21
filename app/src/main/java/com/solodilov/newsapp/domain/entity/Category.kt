package com.solodilov.newsapp.domain.entity


//business entertainment general health science sports technology
enum class Category(val query: String) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
}