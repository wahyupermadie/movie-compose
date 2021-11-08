package id.godohembon.themoviedb.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import id.godohembon.themoviedb.presentation.screen.detail.DetailMovieScreen
import id.godohembon.themoviedb.presentation.screen.main.HomeScreen
import id.godohembon.themoviedb.presentation.screen.movies.MoviesScreen
import id.godohembon.themoviedb.presentation.ui.theme.TheMovieDBTheme
import id.godohembon.themoviedb.utils.MOVIES_TYPE
import id.godohembon.themoviedb.utils.MOVIE_ID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieDBTheme {
                val navController = rememberNavController()
                var homeTitle by remember {
                    mutableStateOf("Home")
                }
                var isHome by remember {
                    mutableStateOf(false)
                }

                navController.addOnDestinationChangedListener { _, destination, arguments ->
                    isHome = false
                    when (destination.route) {
                        "movies/{moviesType}" -> {
                            homeTitle = "Movie List"
                        }
                        "home" -> {
                            homeTitle = "Movie Apps"
                            isHome = true
                        }
                        else -> {
                            homeTitle = "Detail Movie"
                            isHome = false
                        }
                    }
                }

                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            if (!isHome) {
                                TopAppBar(
                                    title = {
                                        Text(
                                            text = homeTitle,
                                            style = MaterialTheme.typography.h6,
                                            textAlign = TextAlign.Start
                                        )
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            navController.navigateUp()
                                        }) {
                                            Icon(Icons.Filled.ArrowBack, "")
                                        }
                                    }
                                )
                            } else {
                                TopAppBar(
                                    title = {
                                        Text(
                                            text = homeTitle,
                                            style = MaterialTheme.typography.h6,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                )
                            }
                        }
                    ) {
                        MainNavigation(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController, mainViewModel = hiltViewModel())
        }
        composable("movie/{movieId}",
            arguments = listOf(navArgument(MOVIE_ID) {
                type = NavType.StringType
            })
        ) {
            val movieId = requireNotNull(it.arguments?.getString(MOVIE_ID))
            DetailMovieScreen(movieId = movieId, mainViewModel = hiltViewModel())
        }
        composable(
            route = "movies/{moviesType}",
            arguments = listOf(navArgument(MOVIES_TYPE) {
                type = NavType.StringType
            })
        ) {
            val moviesType = requireNotNull(it.arguments?.getString(MOVIES_TYPE))
            MoviesScreen(
                navController = navController,
                type = moviesType,
                movieViewModel = hiltViewModel()
            )
        }
    }
}