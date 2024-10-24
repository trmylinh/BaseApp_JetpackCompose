package com.example.baseapp_jetpackcompose.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import coil3.size.Size

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: Any?,
    placeholder: Int = 0,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Inside,
    crossFade: Boolean = false,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(crossFade)
            .error(placeholder)
            .fallback(placeholder)
            .build()
    )

    // collectAsState() để theo dõi trạng thái của painter
    val painterState = painter.state.collectAsState() // Lấy trạng thái hiện tại

    Box {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier,
        )
        if (painterState.value is AsyncImagePainter.State.Loading)
            CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NetworkImage(
        imageUrl = "https://www.sinasamaki.com/content/images/2022/11/Screenshot-2022-11-23-at-08.45.08.png",
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp)
            .clip(shape = RoundedCornerShape(15)),
        contentScale = ContentScale.Crop,
    )

}