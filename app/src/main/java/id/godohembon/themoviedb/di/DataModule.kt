package id.godohembon.themoviedb.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.godohembon.themoviedb.data.dto.movie.MovieResponseDto
import id.godohembon.themoviedb.data.repository.MovieRepository
import id.godohembon.themoviedb.data.repository.MovieRepositoryImpl
import id.godohembon.themoviedb.domain.mapper.MoviesMapper
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.utils.Mapper

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}