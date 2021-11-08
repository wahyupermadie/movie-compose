package id.godohembon.themoviedb.data.service

import id.godohembon.themoviedb.data.dto.DetailMovieResponseDto
import id.godohembon.themoviedb.data.dto.movie.MovieResponseDto
import id.godohembon.themoviedb.data.dto.review.ReviewResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key : String
    ) : Response<MovieResponseDto>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") key : String
    ) : Response<MovieResponseDto>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") key : String
    ) : Response<MovieResponseDto>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") id: Int,
        @Query("api_key") key : String
    ): Response<DetailMovieResponseDto>

    @GET("movie/{movieId}/reviews")
    suspend fun getReview(
        @Path("movieId") id: Int,
        @Query("api_key") key : String
    ): Response<ReviewResponseDto>
}