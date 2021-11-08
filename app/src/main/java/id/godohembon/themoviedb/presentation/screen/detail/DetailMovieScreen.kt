package id.godohembon.themoviedb.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import id.godohembon.themoviedb.R
import id.godohembon.themoviedb.domain.model.DetailMovieModel
import id.godohembon.themoviedb.domain.model.Review
import id.godohembon.themoviedb.presentation.screen.MainViewModel
import id.godohembon.themoviedb.presentation.ui.component.RatingBar
import id.godohembon.themoviedb.presentation.ui.component.home_component.MovieImageComponent
import id.godohembon.themoviedb.presentation.ui.theme.Purple500
import id.godohembon.themoviedb.presentation.ui.theme.TheMovieDBTheme
import id.godohembon.themoviedb.utils.FontHelper.montserratFamily
import id.godohembon.themoviedb.utils.ResourceState
import id.godohembon.themoviedb.utils.getImagePath

@Composable
fun DetailMovieScreen(
    movieId: String,
    mainViewModel: MainViewModel
) {
    val detailMovie = mainViewModel.detailMovie.observeAsState()
    val reviews = mainViewModel.reviews.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.fetchDetailMovie(movieId = movieId.toInt())
        mainViewModel.fetchReviewMovie(movieId = movieId.toInt())
    })

    TheMovieDBTheme {
        when (detailMovie.value) {
            is ResourceState.Success -> {
                val data = (detailMovie.value as ResourceState.Success<DetailMovieModel>).data
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .verticalScroll(
                            state = rememberScrollState(),
                            enabled = true
                        )
                ) {
                    Box(
                        Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = getImagePath(data.backdropPath),
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = "imgUrl",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Column {
                            Card(
                                modifier = Modifier
                                    .padding(top = 150.dp)
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                elevation = 4.dp
                            ) {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 16.dp, horizontal = 24.dp)
                                ) {
                                    Row {
                                        MovieImageComponent(
                                            imgUrl = data.posterPath,
                                            modifier = Modifier
                                                .height(150.dp)
                                                .width(100.dp)
                                        )
                                        Column(
                                            modifier = Modifier.padding(start = 8.dp)
                                        ) {
                                            Text(
                                                text = data.title, style = TextStyle(
                                                    fontFamily = montserratFamily,
                                                    fontWeight = FontWeight.SemiBold,
                                                    fontSize = 18.sp
                                                )
                                            )
                                            Text(text = data.releaseDate)
                                            RatingBar(
                                                rating = data.voteAvg,
                                                modifier = Modifier
                                                    .height(15.dp)
                                                    .align(Alignment.Start)
                                            )
                                        }
                                    }
                                    Text(
                                        text = "Overview", style = TextStyle(
                                            fontFamily = montserratFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp),
                                        text = data.overview,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = montserratFamily
                                        )
                                    )
                                }
                            }
                            ReviewWidget(state = reviews)
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
            is ResourceState.Failure -> {
            }
        }
    }
}

@Composable
fun ReviewWidget(
    state: State<ResourceState<List<Review>>?>
) {
    val configuration = LocalConfiguration.current.screenWidthDp

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        when(state.value) {
            is ResourceState.Success -> {
                val data = (state.value as ResourceState.Success<List<Review>>).data
                Text(
                    text = "Review", style = TextStyle(
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                LazyRow {
                    items(data) { review ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            elevation = 4.dp,
                            modifier = Modifier.width((configuration / 2).dp)
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_user),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(36.dp)
                                        .height(36.dp)
                                        .padding(start = 16.dp, top = 16.dp)
                                )
                                Column(
                                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = review.author, style = TextStyle(
                                            fontFamily = montserratFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = review.content, style = TextStyle(
                                            fontFamily = montserratFamily,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 12.sp
                                        ),
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
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
            is ResourceState.Failure -> {
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetailMovieScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Box(
            Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = getImagePath("/lNyLSOKMMeUPr1RsL4KcRuIXwHt.jpg"),
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "imgUrl",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Column {
                Card(
                    modifier = Modifier
                        .padding(top = 150.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 24.dp)
                    ) {
                        Row {
                            MovieImageComponent(
                                imgUrl = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(100.dp)
                            )
                            Column(
                                modifier = Modifier.padding(start = 8.dp)
                            ) {
                                Text(
                                    text = "Venom: Let There Be Carnage", style = TextStyle(
                                        fontFamily = montserratFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                )
                                Text(text = "2020-12-01")
                                RatingBar(
                                    rating = 4.5f,
                                    modifier = Modifier
                                        .height(15.dp)
                                        .align(Alignment.Start)
                                )
                            }
                        }
                        Text(
                            text = "Overview", style = TextStyle(
                                fontFamily = montserratFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady."
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Review", style = TextStyle(
                            fontFamily = montserratFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 4.dp
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(36.dp)
                                    .height(36.dp)
                                    .padding(start = 16.dp, top = 16.dp)
                            )
                            Column(
                                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                            ) {
                                Text(
                                    text = "Wahyu Permadi", style = TextStyle(
                                        fontFamily = montserratFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Bagus banget ni film cuyy", style = TextStyle(
                                        fontFamily = montserratFamily,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp
                                    ),
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}