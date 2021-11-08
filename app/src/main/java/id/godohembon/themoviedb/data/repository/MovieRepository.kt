package id.godohembon.themoviedb.data.repository

import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.utils.ResourceState

interface MovieRepository {
    suspend fun getPopularMovies() : ResourceState<List<MovieModel>>
    suspend fun getTopRatedMovies() : ResourceState<List<MovieModel>>
    suspend fun getUpcomingMovies() : ResourceState<List<MovieModel>>
    suspend fun getDetailMovie(id: Int) : ResourceState<DetailMovieModel>
    suspend fun getReviewMovie(id: Int) : ResourceState<List<Review>>
}