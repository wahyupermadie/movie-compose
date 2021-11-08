package id.godohembon.themoviedb.domain.model

import com.squareup.moshi.Json
import id.godohembon.themoviedb.data.dto.review.AuthorDetails

data class Review(
    val author: String,
    val content: String,
    val createdAt: String,
    val id: String
)