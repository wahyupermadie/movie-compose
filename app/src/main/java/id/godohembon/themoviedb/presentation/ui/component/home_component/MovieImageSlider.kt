package id.godohembon.themoviedb.presentation.ui.component.home_component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.presentation.ui.component.shimmer.ShimmerComponent
import id.godohembon.themoviedb.utils.ResourceState
import id.godohembon.themoviedb.utils.getImagePath
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieImageSlider(
    navController: NavController,
    state: ResourceState<List<MovieModel>>?,
) {
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState) {
        while (true) {
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (10)
            )
        }
    }

    when (state) {
        is ResourceState.Loading -> {
            ShimmerComponent(
                150,
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )
        }
        is ResourceState.Success -> {
            HorizontalPager(
                count = 10,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 16.dp)
            ) { page ->
                MovieImageComponent(
                    imgUrl = getImagePath(state.data[page].backdropPath),
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        }
                        .clickable(
                            onClick = {
                                navController.navigate("movie/${state.data[page].id}")
                            }
                        )
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                )
            }
        }
        is ResourceState.Failure -> {
            Text(text = "Something Went Wrong ${state.message}")
        }
    }
}