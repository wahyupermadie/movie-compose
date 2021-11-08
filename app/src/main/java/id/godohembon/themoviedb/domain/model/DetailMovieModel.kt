package id.godohembon.themoviedb.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.godohembon.themoviedb.data.dto.BelongsToCollectionDto
import id.godohembon.themoviedb.data.dto.GenreDto
import id.godohembon.themoviedb.data.dto.ProductionCompanyDto
import id.godohembon.themoviedb.data.dto.ProductionCountryDto
import id.godohembon.themoviedb.data.dto.SpokenLanguageDto

data class DetailMovieModel(
    val backdropPath: String,
    val posterPath: String,
    val genres: List<GenreModel>? = listOf(),
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAvg: Float
)