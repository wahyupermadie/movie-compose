package id.godohembon.themoviedb.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.domain.usecase.MovieUseCase
import id.godohembon.themoviedb.utils.ResourceState
import id.godohembon.themoviedb.utils.network.CoroutineDispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val dispatcher: CoroutineDispatchers
): ViewModel() {

    private val _popularMovies = MutableLiveData<ResourceState<List<MovieModel>>>()
    val popularMovies: LiveData<ResourceState<List<MovieModel>>>
        get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<ResourceState<List<MovieModel>>>()
    val topRatedMovies: LiveData<ResourceState<List<MovieModel>>>
        get() = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<ResourceState<List<MovieModel>>>()
    val upcomingMovies: LiveData<ResourceState<List<MovieModel>>>
        get() = _upcomingMovies

    private val _detailMovie = MutableLiveData<ResourceState<DetailMovieModel>>()
    val detailMovie: LiveData<ResourceState<DetailMovieModel>>
        get() = _detailMovie

    private val _reviewsMovie = MutableLiveData<ResourceState<List<Review>>>()
    val reviews: LiveData<ResourceState<List<Review>>>
        get() = _reviewsMovie

    fun fetchPopularMovies() = viewModelScope.launch(dispatcher.ui()) {
        _popularMovies.value = ResourceState.Loading()
         val result = withContext(dispatcher.io()) {
            movieUseCase.getPopularMovies()
        }
        _popularMovies.value = result
    }

    fun fetchTopRatedMovies() = viewModelScope.launch(dispatcher.ui()) {
        _topRatedMovies.value = ResourceState.Loading()
        val result = withContext(dispatcher.io()) {
            movieUseCase.getTopRatedMovies()
        }
        _topRatedMovies.value = result
    }

    fun fetchUpcomingMovies() = viewModelScope.launch(dispatcher.ui()) {
        _upcomingMovies.value = ResourceState.Loading()
        val result = withContext(dispatcher.io()) {
            movieUseCase.getUpcomingMovies()
        }
        _upcomingMovies.value = result
    }

    fun fetchDetailMovie(movieId: Int) = viewModelScope.launch(dispatcher.ui()) {
        _detailMovie.value = ResourceState.Loading()
        val result = withContext(dispatcher.io()) {
            movieUseCase.getDetailMovie(movieId)
        }
        _detailMovie.value = result
    }

    fun fetchReviewMovie(movieId: Int) = viewModelScope.launch(dispatcher.ui()) {
        _reviewsMovie.value = ResourceState.Loading()
        val result = withContext(dispatcher.io()) {
            movieUseCase.getReviewMovie(movieId)
        }
        _reviewsMovie.value = result
    }
}