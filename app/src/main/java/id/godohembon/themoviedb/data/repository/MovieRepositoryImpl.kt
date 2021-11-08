package id.godohembon.themoviedb.data.repository

import id.godohembon.themoviedb.BuildConfig
import id.godohembon.themoviedb.data.dto.DetailMovieResponseDto
import id.godohembon.themoviedb.data.dto.movie.MovieResponseDto
import id.godohembon.themoviedb.data.dto.review.ReviewResponseDto
import id.godohembon.themoviedb.data.service.MovieService
import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.utils.Mapper
import id.godohembon.themoviedb.utils.ResourceState
import id.godohembon.themoviedb.utils.safeApiCall
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val moviesMapper: Mapper<MovieResponseDto, List<MovieModel>>,
    private val movieMapper: Mapper<DetailMovieResponseDto, DetailMovieModel>,
    private val reviewMapper: Mapper<ReviewResponseDto, List<Review>>
) : MovieRepository {

    override suspend fun getPopularMovies(): ResourceState<List<MovieModel>> {
        return safeApiCall(moviesMapper) {
            movieService.getPopularMovies(BuildConfig.API_KEY)
        }
    }

    override suspend fun getTopRatedMovies(): ResourceState<List<MovieModel>> {
        return safeApiCall(moviesMapper) {
            movieService.getTopRatedMovies(BuildConfig.API_KEY)
        }
    }

    override suspend fun getUpcomingMovies(): ResourceState<List<MovieModel>> {
        return safeApiCall(moviesMapper) {
            movieService.getUpcomingMovies(BuildConfig.API_KEY)
        }
    }

    override suspend fun getDetailMovie(id: Int): ResourceState<DetailMovieModel> {
        return safeApiCall(movieMapper) {
            movieService.getMovieDetail(
                id,
                BuildConfig.API_KEY
            )
        }
    }

    override suspend fun getReviewMovie(id: Int): ResourceState<List<Review>> {
        return safeApiCall(reviewMapper) {
            movieService.getReview(
                id,
                BuildConfig.API_KEY
            )
        }
    }
}