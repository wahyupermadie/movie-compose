package id.godohembon.themoviedb.presentation.ui.component.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerComponent(
    height: Int, modifier: Modifier
) {

    val configuration = LocalConfiguration.current.screenWidthDp
    val pxValue = LocalDensity.current.run {
        (configuration.dp - 16.dp).toPx()
    }

    val shimmerColorShades = listOf(
        Color.LightGray.copy(alpha = .9f),
        Color.LightGray.copy(alpha = .3f),
        Color.LightGray.copy(alpha = .9f),
    )

    /*
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
    */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /*
        Specify animation positions,
        initial Values 0F means it starts from 0 position
        */

        initialValue = 0f,
        targetValue = pxValue,
        animationSpec = infiniteRepeatable(

            /*
             Tween Animates between values over specified [durationMillis]
            */
            tween(durationMillis = 1300, easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    /*
      Create a gradient using the list of colors
      Use Linear Gradient for animating in any direction according to requirement
      start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
      end= Animate the end position to give the shimmer effect using the transition created above
    */

    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(translateAnim, translateAnim),
        end = Offset(200f + translateAnim, 200f + translateAnim)
    )

    ShimmerItem(brush = brush, height = height, modifier = modifier)
}

@Composable
fun ShimmerItem(
    brush: Brush,
    height: Int,
    modifier: Modifier
) {

    /*
      Column composable shaped like a rectangle,
      set the [background]'s [brush] with the
      brush receiving from [ShimmerAnimation]
      which will get animated.
      Add few more Composable to test
    */
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .size(height.dp)
            .background(brush = brush, shape = RoundedCornerShape(8.dp))
    )
}
