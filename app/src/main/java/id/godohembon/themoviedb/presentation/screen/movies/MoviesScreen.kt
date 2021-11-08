package id.godohembon.themoviedb.presentation.screen.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.presentation.screen.MainViewModel
import id.godohembon.themoviedb.presentation.ui.component.home_component.MovieComponent
import id.godohembon.themoviedb.presentation.ui.theme.Purple500
import id.godohembon.themoviedb.presentation.ui.theme.TheMovieDBTheme
import id.godohembon.themoviedb.utils.FontHelper
import id.godohembon.themoviedb.utils.MovieType
import id.godohembon.themoviedb.utils.ResourceState

@Composable
fun MoviesScreen(
    navController: NavController,
    type: String,
    movieViewModel: MainViewModel
) {

    val response = (when (type) {
        MovieType.Popular.name -> {
            movieViewModel.popularMovies
        }
        MovieType.TopRated.name -> {
            movieViewModel.topRatedMovies
        }
        else -> {
            movieViewModel.upcomingMovies
        }
    }).observeAsState().value

    LaunchedEffect(Unit) {
        when (type) {
            MovieType.Popular.name -> {
                movieViewModel.fetchPopularMovies()
            }
            MovieType.TopRated.name -> {
                movieViewModel.fetchTopRatedMovies()
            }
            else -> {
                movieViewModel.fetchUpcomingMovies()
            }
        }
    }

    TheMovieDBTheme {
        when (response) {
            is ResourceState.Success -> {
                LazyColumn {
                    val result = response.data

                    items(items = result.chunked(2)) { rowItems ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            rowItems.forEach { movieModel ->
                                Column(
                                    Modifier.weight(1f)
                                ) {
                                    MovieComponent(
                                        Modifier.fillParentMaxWidth(),
                                        movieModel
                                    ) {
                                        navController.navigate("movie/${it.id}}")
                                    }
                                    Text(
                                        text = movieModel.name, style = TextStyle(
                                            fontFamily = FontHelper.montserratFamily,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 16.sp
                                        ),
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
            is ResourceState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp),
                        color = Purple500,
                        strokeWidth = Dp(value = 4F)
                    )
                }
            }
            is ResourceState.Failure -> {}
        }
    }
}
