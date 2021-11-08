package id.godohembon.themoviedb.data.dto.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponseDto(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val resultDtos: List<ResultDto>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)