package id.godohembon.themoviedb.presentation.ui.component.home_component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.godohembon.themoviedb.domain.model.MovieModel

@Composable
fun MovieComponent(
    modifier: Modifier,
    movieModel: MovieModel,
    onMovieClick: (MovieModel) -> Unit
) {
    Column(modifier = modifier
        .clickable {
            onMovieClick.invoke(movieModel)
        }
    ) {
        MovieImageComponent(
            imgUrl = movieModel.posterPath,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        )
    }
}