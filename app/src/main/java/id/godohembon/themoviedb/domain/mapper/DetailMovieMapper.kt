package id.godohembon.themoviedb.domain.mapper

import id.godohembon.themoviedb.data.dto.DetailMovieResponseDto
import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.GenreModel
import id.godohembon.themoviedb.utils.Mapper

class DetailMovieMapper : Mapper<DetailMovieResponseDto, DetailMovieModel> {
    override fun map(input: DetailMovieResponseDto): DetailMovieModel {
        return with(input) {
            DetailMovieModel(
                backdropPath = backdropPath ?: "",
                genres = genreDtos?.map {
                    GenreModel(
                        it.id ?: 0,
                        it.name ?: ""
                    )
                },
                id = id ?: 0,
                originalLanguage = originalLanguage ?: "",
                originalTitle = originalTitle ?: "",
                overview = overview ?: "",
                releaseDate = releaseDate ?: "",
                runtime = runtime ?: 0,
                title = title ?: "",
                voteAvg = voteAverage ?: 0f,
                posterPath = posterPath ?: ""
            )
        }
    }
}