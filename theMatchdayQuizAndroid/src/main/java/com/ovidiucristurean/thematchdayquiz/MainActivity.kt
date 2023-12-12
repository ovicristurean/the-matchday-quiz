package com.ovidiucristurean.thematchdayquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.ovidiucristurean.thematchdayquiz.service.activityprovider_api.ActivitySetter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    private val activitySetter by inject<ActivitySetter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO migrate to KTX API from Firebase
        Firebase.initialize(this)
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                resourcePath = "nothing"
            )
        }
    }

    override fun onResume() {
        super.onResume()
        activitySetter.set(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        activitySetter.clear()
    }

}

@Preview
@Composable
fun DefaultPreview() {
    App(
        darkTheme = isSystemInDarkTheme(),
        resourcePath = "resourcePath"
    )
}
