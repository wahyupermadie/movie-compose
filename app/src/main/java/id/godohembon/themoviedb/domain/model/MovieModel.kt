package id.godohembon.themoviedb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val name: String = "",
    val posterPath: String? = "",
    val backdropPath: String? = ""
) : Parcelable
