package id.godohembon.themoviedb.domain.usecase

import id.godohembon.themoviedb.data.repository.MovieRepository
import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.utils.ResourceState
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): MovieUseCase {
    override suspend fun getPopularMovies(): ResourceState<List<MovieModel>> {
        return movieRepository.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): ResourceState<List<MovieModel>> {
        return movieRepository.getTopRatedMovies()
    }

    override suspend fun getUpcomingMovies(): ResourceState<List<MovieModel>> {
        return movieRepository.getUpcomingMovies()
    }

    override suspend fun getDetailMovie(id: Int): ResourceState<DetailMovieModel> {
        return movieRepository.getDetailMovie(id)
    }

    override suspend fun getReviewMovie(id: Int): ResourceState<List<Review>> {
        return movieRepository.getReviewMovie(id)
    }
}