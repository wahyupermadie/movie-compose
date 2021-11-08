package id.godohembon.themoviedb.presentation.ui.component.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun HomeShimmerComponent() {
    val configuration = LocalConfiguration.current.screenWidthDp

    Column {
        repeat(5) {
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
}