package id.godohembon.themoviedb.presentation.ui.component.home_component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import id.godohembon.themoviedb.domain.model.MovieModel
import id.godohembon.themoviedb.presentation.ui.component.shimmer.ShimmerComponent
import id.godohembon.themoviedb.utils.FontHelper.montserratFamily
import id.godohembon.themoviedb.utils.ResourceState

@Composable
fun MovieSectionComponent(
    title: String,
    actionName: String,
    state: ResourceState<List<MovieModel>>?,
    action: (List<MovieModel>) -> Unit,
    onMovieClick: (MovieModel) -> Unit,
) {
    val configuration = LocalConfiguration.current.screenWidthDp

    when(state) {
        is ResourceState.Success -> {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (sectionTitle, sectionAction) = createRefs()

                    Text(
                        text = title,
                        modifier = Modifier.constrainAs(sectionTitle) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(sectionAction.start, margin = 18.dp)
                            width = Dimension.fillToConstraints
                        },
                        style = TextStyle(
                            fontFamily = montserratFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )

                    Text(
                        text = actionName, modifier = Modifier
                            .constrainAs(sectionAction) {
                                end.linkTo(parent.end, margin = 16.dp)
                                top.linkTo(parent.top, margin = 16.dp)
                            }
                            .clickable {
                                action.invoke(state.data)
                            },
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontFamily = montserratFamily,
                            fontSize = 14.sp
                        )
                    )
                }

                LazyRow(
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    itemsIndexed(state.data.take(4)) { position, movie ->
                        Spacer(modifier = Modifier.size(16.dp))
                        MovieImageComponent(
                            imgUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                            modifier = Modifier.clickable(
                                onClick = {
                                    onMovieClick.invoke(movie)
                                }
                            )
                        )
                        if (position == 3) {
                            Spacer(modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }
        is ResourceState.Loading -> {
            Column {
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ShimmerComponent(24, Modifier.width(configuration.dp / 2))

                    ShimmerComponent(24, Modifier.width(configuration.dp / 3))
                }
                Spacer(modifier = Modifier.size(16.dp))
                LazyRow {
                    items(4) {
                        Spacer(modifier = Modifier.width(16.dp))
                        ShimmerComponent(150, Modifier.width(100.dp))
                    }
                }
            }
        }
        is ResourceState.Failure -> { }
    }
}