package id.godohembon.themoviedb.di

import android.app.Application
import android.graphics.Movie
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.godohembon.themoviedb.BuildConfig
import id.godohembon.themoviedb.data.service.MovieService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        application: Application
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(ChuckInterceptor(application))
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}