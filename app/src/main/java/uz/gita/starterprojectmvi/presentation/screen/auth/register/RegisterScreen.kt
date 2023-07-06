package uz.gita.starterprojectmvi.presentation.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.presentation.screen.auth.login.LoginContract
import uz.gita.starterprojectmvi.ui.theme.MyColor1
import uz.gita.starterprojectmvi.utils.navigation.MyScreen

class RegisterScreen : MyScreen() {
    @Composable
    override fun Content() {
        val screenModel = getViewModel<RegisterModel>()
        val context = LocalContext.current

        screenModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is RegisterContract.SideEffect.HasError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        val uiState = screenModel.collectAsState().value
        SignUpContent(uiState, screenModel::eventDispatcher)
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Composable
    private fun SignUpContent(
        uiState: RegisterContract.UiState,
        eventDispatcher: (RegisterContract.Intent) -> Unit,
    ) {


        var emailInput by remember { mutableStateOf("") }
        var nameInput by remember { mutableStateOf("") }
        var passwordInput by remember { mutableStateOf("") }
        var visablePassword by remember { mutableStateOf(false) }

        FloatingActionButton(
            onClick = { /*TODO*/ },
            shape = CircleShape,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            Image(imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Back Info",
                modifier = Modifier
                    .combinedClickable {
                        eventDispatcher.invoke(RegisterContract.Intent.Back)
                    }
                    .size(32.dp)
                    .rotate(90f))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.register_pic),
                contentDescription = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(253.dp)
                    .padding(top = 60.dp)
            )
            Text(
                text = "Sign up",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Create your accaunt ",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(4.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(16.dp)),
                value = nameInput,
                onValueChange = {
                    nameInput = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "Name", color = Color.Gray) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)

            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
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
                placeholder = { Text(text = "E-mail", color = Color.Gray) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)

            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
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
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 71.dp)
                    .padding(bottom = 16.dp),
                onClick = {
                    eventDispatcher(
                        RegisterContract.Intent.CreateUser(
                            emailInput,
                            passwordInput,
                            name = nameInput
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(MyColor1)
            ) {
                Text(text = "Sign up")
            }
            Text(
                text = "Log in",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 60.dp)
                    .clickable {
                        eventDispatcher.invoke(RegisterContract.Intent.Back)
                    },
                textAlign = TextAlign.Center
            )
        }
        when (uiState) {
            RegisterContract.UiState.Welcome -> {

            }

            RegisterContract.UiState.Loading -> {
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

    @Preview
    @Composable
    fun prewRegister() {
        Surface {
            SignUpContent(RegisterContract.UiState.Loading) {

            }
        }
    }
}

