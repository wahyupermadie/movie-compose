package id.godohembon.themoviedb

import androidx.lifecycle.Observer
import id.godohembon.themoviedb.data.MoviesResponse
import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.domain.usecase.MovieUseCase
import id.godohembon.themoviedb.external.InstantTaskExecutor
import id.godohembon.themoviedb.presentation.screen.MainViewModel
import id.godohembon.themoviedb.utils.ResourceState
import id.godohembon.themoviedb.utils.network.CoroutineDispatchers
import id.godohembon.themoviedb.utils.network.TestDispatcher
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutor::class)
class MainViewModelTest {

    private var movieUseCase: MovieUseCase = mockk(relaxed = true)
    private lateinit var testDispatch: CoroutineDispatchers
    private lateinit var mainViewModel: MainViewModel
    private var popularObserver: Observer<ResourceState<List<MovieModel>>> = mockk()
    private var upcomingObserver: Observer<ResourceState<List<MovieModel>>> = mockk(relaxed = true)
    private var topRatedObserver: Observer<ResourceState<List<MovieModel>>> = mockk(relaxed = true)
    private var detailObserver: Observer<ResourceState<DetailMovieModel>> = mockk(relaxed = true)
    private var reviewsObserver: Observer<ResourceState<List<Review>>> = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        testDispatch = TestDispatcher()

        mainViewModel = MainViewModel(movieUseCase = movieUseCase, testDispatch).apply {
            popularMovies.observeForever(popularObserver)
            upcomingMovies.observeForever(upcomingObserver)
            topRatedMovies.observeForever(topRatedObserver)
            detailMovie.observeForever(detailObserver)
            reviews.observeForever(reviewsObserver)
        }
    }

    @Nested
    @DisplayName("Given Main View Model")
    inner class GivenMainViewModel {

        @Nested
        @DisplayName("When Get Popular Movies Return Success")
        inner class OnSuccessCase() {

            @DisplayName("When MovieUseCase Get Popular Movies Return Success")
            @BeforeEach
            fun fetchMovieUseCase() {
                coEvery {
                    movieUseCase.getPopularMovies()
                } coAnswers {
                    ResourceState.Success(MoviesResponse.getMovies())
                }
            }

            @DisplayName("Then Movie Result State Should Be Success")
            @Test
            fun fetchMainViewModel() {
                val slot = slot<ResourceState<List<MovieModel>>>()
                val expected = mutableListOf<ResourceState<List<MovieModel>>>()
                every { popularObserver.onChanged(capture(slot)) } answers {
                    expected.add(slot.captured)
                }

                mainViewModel.fetchPopularMovies()
                TestCase.assertEquals(expected.size, 2)
                assert(expected[0] is ResourceState.Loading)
                assert(expected[1] is ResourceState.Success)

                coVerify { movieUseCase.getPopularMovies() }
                confirmVerified(movieUseCase)
            }
        }

        @Nested
        @DisplayName("When Get Popular Movies Return Failure")
        inner class OnFailureCase() {

            @DisplayName("When MovieUseCase Get Popular Movies Return Failure")
            @BeforeEach
            fun fetchMovieUseCase() {
                coEvery {
                    movieUseCase.getPopularMovies()
                } coAnswers {
                    ResourceState.Failure(Throwable(), message = "Internal Server Error", responseCode = 500)
                }
            }

            @DisplayName("Then Movie Result State Should Be Failure")
            @Test
            fun fetchMainViewModel() {
                val slot = slot<ResourceState<List<MovieModel>>>()
                val expected = mutableListOf<ResourceState<List<MovieModel>>>()
                every { popularObserver.onChanged(capture(slot)) } answers {
                    expected.add(slot.captured)
                }

                mainViewModel.fetchPopularMovies()
                TestCase.assertEquals(expected.size, 2)
                assert(expected[0] is ResourceState.Loading)
                assert(expected[1] is ResourceState.Failure)
                TestCase.assertEquals((expected[1] as ResourceState.Failure).message, "Internal Server Error")
                coVerify { movieUseCase.getPopularMovies() }
                confirmVerified(movieUseCase)
            }
        }
    }
}