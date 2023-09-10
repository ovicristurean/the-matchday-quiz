package com.ovidiucristurean.thematchdayquiz.ui.screens.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.AvailableQuizState
import com.seiko.imageloader.rememberImagePainter

@Composable
fun AvailableQuizView(
    modifier: Modifier,
    currentQuizState: AvailableQuizState,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        val painter = when (currentQuizState) {
            is AvailableQuizState.CurrentQuizNotAvailable -> {
                rememberVectorPainter(Icons.Default.Lock)
            }

            is AvailableQuizState.QuizAvailable -> {
                rememberImagePainter(currentQuizState.quizImageUrl)
            }
        }

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
