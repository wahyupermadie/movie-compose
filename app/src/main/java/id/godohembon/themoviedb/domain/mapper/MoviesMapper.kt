package id.godohembon.themoviedb.domain.mapper

import id.godohembon.themoviedb.data.dto.movie.MovieResponseDto
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.utils.Mapper

class MoviesMapper: Mapper<MovieResponseDto, List<MovieModel>?> {
    override fun map(input: MovieResponseDto): List<MovieModel>? {
        return input.resultDtos?.let {
            it.map { result ->
                MovieModel(
                    id = result.id,
                    name = result.originalTitle ?: "",
                    posterPath = result.posterPath,
                    backdropPath = result.backdropPath
                )
            }
        }
    }
}