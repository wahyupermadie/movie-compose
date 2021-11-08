package id.godohembon.themoviedb.data.dto.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponseDto(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<Result>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)