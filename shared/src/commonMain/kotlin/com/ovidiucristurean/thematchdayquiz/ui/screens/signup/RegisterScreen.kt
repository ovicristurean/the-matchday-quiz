package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.thematchdayquiz.ui.navigation.MainScreen
import com.ovidiucristurean.widgets.button.MatchdayButton
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterScreen : Screen, KoinComponent {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val viewModel: RegisterViewModel by inject()
        val keyboardController = LocalSoftwareKeyboardController.current
        val navigator = LocalNavigator.currentOrThrow

        var emailValue by rememberSaveable {
            mutableStateOf("")
        }

        var passwordValue by rememberSaveable {
            mutableStateOf("")
        }

        LaunchedEffect(viewModel.authState) {
            viewModel.authState.collect {
                when (it) {
                    LoginResult.LOGGED -> {
                        navigator.push(MainScreen())
                    }

                    LoginResult.NOT_LOGGED -> {
                        viewModel.showErrorMessage("Error while attempting login")
                    }

                    LoginResult.IDLE -> {
                        viewModel.showErrorMessage("App idle")
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary)
        ) {
            ScreenContent(
                screenTitle = "Register to Matchday Quiz",
                onEmailChanged = {
                    emailValue = it
                },
                onPasswordChanged = {
                    passwordValue = it
                },
                onAuthenticationRequested = {
                    viewModel.register(emailValue, passwordValue)
                },
                keyboardController = keyboardController
            )
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun ScreenContent(
        screenTitle: String,
        onEmailChanged: (String) -> Unit,
        onPasswordChanged: (String) -> Unit,
        onAuthenticationRequested: () -> Unit,
        keyboardController: SoftwareKeyboardController?
    ) {
        val screenContentPadding = 16.dp
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(all = screenContentPadding)
        ) {
            item {
                AuthScreenLogo(
                    modifier = Modifier
                        .padding(
                            top = screenContentPadding * 4,
                            bottom = screenContentPadding
                        )
                )
            }

            item {
                AuthScreenTitle(
                    title = screenTitle,
                    modifier = Modifier.padding(bottom = screenContentPadding * 2)
                )
            }

            item {
                EmailInput(
                    onEmailChanged = onEmailChanged,
                    emailPlaceholder = "E-mail",
                    modifier = Modifier.padding(bottom = screenContentPadding)
                )
            }

            item {
                PasswordInput(
                    passwordPlaceholder = "Password",
                    onPasswordChange = onPasswordChanged,
                    keyboardActions = {
                        onAuthenticationRequested()
                        keyboardController?.hide()
                    },
                    modifier = Modifier.padding(bottom = screenContentPadding * 2)
                )
            }

            item {
                MatchdayButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAuthenticationRequested()
                    }
                ) {
                    Text(
                        text = "Register",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Composable
    fun AuthScreenLogo(
        modifier: Modifier = Modifier
    ) {
        Image(
            painter = rememberVectorPainter(Icons.Default.AppRegistration),
            contentDescription = null,
            modifier = modifier.size(100.dp)
        )
    }

    @Composable
    fun AuthScreenTitle(
        title: String,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }

    @Composable
    fun EmailInput(
        modifier: Modifier = Modifier,
        emailPlaceholder: String,
        onEmailChanged: (String) -> Unit
    ) {
        var emailValue by remember {
            mutableStateOf("")
        }
        AuthInputField(
            value = emailValue,
            onValueChange = {
                emailValue = it
                onEmailChanged(it)
            },
            placeholder = emailPlaceholder,
            leadingIcon = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.None
            ),
            modifier = modifier
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun AuthInputField(
        modifier: Modifier = Modifier,
        value: String,
        onValueChange: (String) -> Unit,
        label: @Composable (() -> Unit)? = null,
        placeholder: String,
        leadingIcon: ImageVector,
        trailingIcon: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions(),
    ) {
        val isDark = isSystemInDarkTheme()

        OutlinedTextField(
            value = value.trim(),
            onValueChange = onValueChange,
            label = label,
            placeholder = {
                Text(
                    text = placeholder,
                    color = if (isDark) Color.LightGray else Color.DarkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = if (isDark) Color.LightGray else Color.DarkGray,
                    modifier = Modifier.size(size = 20.dp)
                )
            },
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = Color.LightGray.copy(alpha = if (isSystemInDarkTheme()) 0.4f else 1f)
            ),
            singleLine = true,
            modifier = modifier.fillMaxWidth()
        )
    }

    @Composable
    private fun PasswordInput(
        modifier: Modifier = Modifier,
        passwordPlaceholder: String,
        onPasswordChange: (String) -> Unit,
        keyboardActions: (() -> Unit) = { }
    ) {
        var passwordVisible by rememberSaveable { mutableStateOf(value = false) }
        var passwordValue by remember { mutableStateOf("") }

        AuthInputField(
            value = passwordValue,
            onValueChange = {
                passwordValue = it
                onPasswordChange(it)
            },
            placeholder = passwordPlaceholder,
            leadingIcon = Icons.Default.Lock,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { keyboardActions() }),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                        modifier = Modifier.size(size = 20.dp)
                    )
                }
            },
            modifier = modifier
        )
    }
}
