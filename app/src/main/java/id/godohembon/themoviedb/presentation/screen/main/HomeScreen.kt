package id.godohembon.themoviedb.presentation.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.godohembon.themoviedb.R
import id.godohembon.themoviedb.presentation.screen.MainViewModel
import id.godohembon.themoviedb.presentation.ui.component.home_component.MovieImageSlider
import id.godohembon.themoviedb.presentation.ui.component.home_component.MovieSectionComponent
import id.godohembon.themoviedb.presentation.ui.theme.TheMovieDBTheme
import id.godohembon.themoviedb.utils.MovieType

@Composable
fun HomeScreen(navController: NavController, mainViewModel: MainViewModel) {

    val popularMovies = mainViewModel.popularMovies.observeAsState()
    val topRatedMovies = mainViewModel.topRatedMovies.observeAsState()
    val upcomingMovies = mainViewModel.upcomingMovies.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.fetchPopularMovies()
        mainViewModel.fetchTopRatedMovies()
        mainViewModel.fetchUpcomingMovies()
    })

    TheMovieDBTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.verticalScroll(
                    state = rememberScrollState(),
                    enabled = true
                )
            ) {
                MovieImageSlider(navController, popularMovies.value)

                MovieSectionComponent(
                    title = stringResource(R.string.txt_popular_movies),
                    actionName = stringResource(R.string.txt_see_all),
                    state = popularMovies.value,
                    action = {
                        navController.apply {
                            navigate("movies/${MovieType.Popular}")
                        }
                    },
                    onMovieClick = {
                        navController.navigate("movie/${it.id}")
                    }
                )

                MovieSectionComponent(
                    title = stringResource(R.string.txt_top_rated_movies),
                    actionName = stringResource(R.string.txt_see_all),
                    state = topRatedMovies.value,
                    action = {
                        navController.apply {
                            navigate("movies/${MovieType.TopRated}")
                        }
                    },
                    onMovieClick = {
                        navController.navigate("movie/${it.id}")
                    }
                )

                MovieSectionComponent(
                    title = stringResource(R.string.txt_upcoming_movies),
                    actionName = stringResource(R.string.txt_see_all),
                    state = upcomingMovies.value,
                    action = {
                        navController.apply {
                            navigate("movies/${MovieType.Upcoming}")
                        }
                    },
                    onMovieClick = {
                        navController.navigate("movie/${it.id.toString()}")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}