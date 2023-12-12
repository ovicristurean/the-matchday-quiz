package com.ovidiucristurean.thematchdayquiz.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


class SplashScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        var animationStarted by remember {
            mutableStateOf(false)
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),

            contentAlignment = Alignment.Center
        ) {

            LaunchedEffect(Unit) {
                animationStarted = true
            }

            AnimatedVisibility(
                visible = animationStarted,
                enter = fadeIn(
                    animationSpec = tween(2000),
                    initialAlpha = 0.2f
                )
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource("endless-constellation.png"),
                    contentDescription = "Compose Multiplatform icon",
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleText(
                    text = "The",
                    color = MaterialTheme.colorScheme.primary,
                    animationStarted = animationStarted,
                    initialTranslationOffset = -3000f
                )

                TitleText(
                    text = "Matchday",
                    color = MaterialTheme.colorScheme.secondary,
                    animationStarted = animationStarted,
                    initialTranslationOffset = 3000f
                )

                TitleText(
                    text = "Quiz",
                    color = MaterialTheme.colorScheme.tertiary,
                    animationStarted = animationStarted,
                    initialTranslationOffset = -3000f
                )
            }
        }
    }
}

@Composable
private fun TitleText(
    text: String,
    color: Color,
    animationStarted: Boolean,
    initialTranslationOffset: Float
) {
    val transition = updateTransition(targetState = animationStarted, label = "")
    val scaleAnimation by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 2000, easing = FastOutSlowInEasing)
        },
        label = ""
    ) {
        when (it) {
            true -> 1f
            false -> 0f
        }
    }

    val translationAnimation by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            )
        },
        label = ""
    ) {
        when (it) {
            true -> 0f
            false -> initialTranslationOffset
        }
    }

    val alphaAnimation by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 4000,
                easing = LinearEasing
            )
        },
        label = ""
    ) {
        when (it) {
            true -> 1f
            false -> 0f
        }
    }

    Text(
        modifier = Modifier
            .graphicsLayer {
                translationX = translationAnimation
                scaleX = scaleAnimation
                scaleY = scaleAnimation
                alpha = alphaAnimation
            },
        text = text,
        textAlign = TextAlign.Center,
        color = color,
        style = LocalTextStyle.current.merge(
            TextStyle(
                fontSize = 80.sp,
            )
        )
    )
}