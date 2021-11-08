package id.godohembon.themoviedb.domain.model

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