package uz.gita.starterprojectmvi.presentation.screen.auth.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.ui.theme.MyColor1
import uz.gita.starterprojectmvi.ui.theme.StarterProjectMVITheme
import uz.gita.starterprojectmvi.utils.navigation.MyScreen

class LoginScreen : MyScreen() {
    @Composable
    override fun Content() {
        val viewModel: LoginContract.Model = getViewModel<LoginModel>()
        val context = LocalContext.current

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is LoginContract.SideEffect.ErrorWrongLoginOrPassword -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }

                is LoginContract.SideEffect.ErrorInternetConnection -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        val uiState = viewModel.collectAsState().value
        ContentScreen(uiState, viewModel::eventDispatcher)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ContentScreen(
    uiState: LoginContract.UiState,
    eventDispatcher: (LoginContract.Intent) -> Unit,
) {

    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var visablePassword by remember { mutableStateOf(false) }

    StarterProjectMVITheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_pic),
                    contentDescription = "Login",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(253.dp)
                        .padding(top = 70.dp)
                )
                Text(
                    text = "Log in",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Login with sogical networks ",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(id = R.drawable.sogical_buttons),
                    contentDescription = "Sogical",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(16.dp)),
                    value = emailInput,
                    onValueChange = {
                        emailInput = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Email", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(16.dp)),
                    value = passwordInput,
                    onValueChange = {
                        passwordInput = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Password", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        if (visablePassword) {
                            Icon(
                                painter = painterResource(id = R.drawable.show),
                                contentDescription = "Showed",
                                tint = Color.Gray,
                                modifier = Modifier.clickable {
                                    visablePassword = !visablePassword
                                })
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.hide),
                                contentDescription = "Hidden",
                                tint = Color.Gray,
                                modifier = Modifier.clickable {
                                    visablePassword = !visablePassword
                                })
                        }
                    },
                    visualTransformation = if (visablePassword) VisualTransformation.None else PasswordVisualTransformation()
                )
                Text(
                    text = "Forgot password?",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 58.dp),
                    onClick = {
                        eventDispatcher(LoginContract.Intent.LoginUser(emailInput, passwordInput))
                    },
                    colors = ButtonDefaults.buttonColors(MyColor1),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Log in", fontSize = MaterialTheme.typography.titleLarge.fontSize)
                }
                Text(
                    text = "Sign up",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 70.dp)
                        .clickable {
                            eventDispatcher.invoke(LoginContract.Intent.OpenSignUp)
                        },
                    textAlign = TextAlign.Center
                )

            }


            when (uiState) {
                LoginContract.UiState.Welcome -> {

                }

                LoginContract.UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            strokeWidth = 7.dp,
                            color = MyColor1
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        ContentScreen(LoginContract.UiState.Welcome) {}
    }
}