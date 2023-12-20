package com.ovidiucristurean.thematchdayquiz.ui.screens.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeveloperMode
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenTopBar(
    onUserProfileClicked: () -> Unit,
    onDeveloperSettingsClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp)
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onDeveloperSettingsClicked()
                },
            imageVector = Icons.Rounded.DeveloperMode,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )

        Icon(
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onUserProfileClicked()
                },
            imageVector = Icons.Rounded.Settings,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
