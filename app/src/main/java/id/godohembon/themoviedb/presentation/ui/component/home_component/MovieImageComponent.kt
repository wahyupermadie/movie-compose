package id.godohembon.themoviedb.presentation.ui.component.home_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import id.godohembon.themoviedb.utils.getImagePath

@Composable
fun MovieImageComponent(
    imgUrl: String?,
    modifier: Modifier
) {
    Image(
        painter = rememberImagePainter(
            data = getImagePath(imgUrl),
            builder = {
                transformations(RoundedCornersTransformation(16f))
            }
        ),
        contentScale = ContentScale.Crop,
        contentDescription = imgUrl,
        modifier = modifier
            .width(128.dp)
            .height(180.dp)
    )
}