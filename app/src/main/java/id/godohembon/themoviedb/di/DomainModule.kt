package id.godohembon.themoviedb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.godohembon.themoviedb.domain.usecase.MovieUseCase
import id.godohembon.themoviedb.domain.usecase.MovieUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsMovieUseCase(movieUseCase: MovieUseCaseImpl): MovieUseCase
}