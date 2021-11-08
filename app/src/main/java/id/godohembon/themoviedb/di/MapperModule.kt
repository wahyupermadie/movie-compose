package id.godohembon.themoviedb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.godohembon.themoviedb.data.dto.DetailMovieResponseDto
import id.godohembon.themoviedb.data.dto.movie.MovieResponseDto
import id.godohembon.themoviedb.data.dto.review.ReviewResponseDto
import id.godohembon.themoviedb.domain.mapper.DetailMovieMapper
import id.godohembon.themoviedb.domain.mapper.MoviesMapper
import id.godohembon.themoviedb.domain.mapper.ReviewMapper
import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.utils.Mapper

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Provides
    fun provideMoviesMapper(): Mapper<MovieResponseDto, List<MovieModel>?> = MoviesMapper()

    @Provides
    fun provideReviewMapper(): Mapper<ReviewResponseDto, List<Review>?> = ReviewMapper()

    @Provides
    fun provideMovieMapper(): Mapper<DetailMovieResponseDto, DetailMovieModel> = DetailMovieMapper()
}