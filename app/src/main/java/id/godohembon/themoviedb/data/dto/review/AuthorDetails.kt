package id.godohembon.themoviedb.data.dto.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetails(
    @Json(name = "avatar_path")
    val avatarPath: Any?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "rating")
    val rating: Any?,
    @Json(name = "username")
    val username: String?
)