package com.ovidiucristurean.thematchdayquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO migrate to KTX API from Firebase
        Firebase.initialize(this)
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    App(
        darkTheme = isSystemInDarkTheme(),
    )
}
