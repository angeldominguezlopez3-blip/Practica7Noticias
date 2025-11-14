package com.example.practica7noticias

data class Articles (
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val content: String?,
    val publishedAt: String?,
    val source: Source?
)

data class Source(
    val id: String?,
    val name: String?
)