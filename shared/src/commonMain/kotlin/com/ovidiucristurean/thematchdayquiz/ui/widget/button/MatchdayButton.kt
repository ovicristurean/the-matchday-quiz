package com.ovidiucristurean.thematchdayquiz.ui.widget.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.thematchdayquiz.ui.widget.modifier.pressClickEffect

@Composable
fun MatchdayButton(
    modifier: Modifier = Modifier.wrapContentSize(),
    onClick: () -> Unit = {},
    buttonColor: Color = MaterialTheme.colorScheme.secondary,
    depthColor: Color = MaterialTheme.colorScheme.onSecondary,
    buttonDepth: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(12.dp),
    isEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val buttonShape by remember { mutableStateOf(shape) }
    Box(
        modifier = modifier.width(IntrinsicSize.Max)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = depthColor,
            ),
            onClick = {}
        ) {

        }
        Button(
            modifier = Modifier.fillMaxWidth().pressClickEffect(
                clickDepth = buttonDepth,
                enabled = isEnabled,
            ),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
            ),
            enabled = isEnabled,
            onClick = onClick,
        ) {
            content()
        }
    }
}
