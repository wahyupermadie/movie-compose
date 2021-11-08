package id.godohembon.themoviedb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.godohembon.themoviedb.utils.network.AppDispatcher
import id.godohembon.themoviedb.utils.network.CoroutineDispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsAppDispatcher(appDispatcher: AppDispatcher): CoroutineDispatchers
}