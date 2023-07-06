package uz.gita.starterprojectmvi.presentation.screen.home.page2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.starterprojectmvi.presentation.screen.home.HomeScreen
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.ui.theme.StarterProjectMVITheme

class Page2Tab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Profile"
            val icon = painterResource(id = R.drawable.nav_profile)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val parentScreen = LocalNavigator.current?.parent?.lastItem // HomeScreen
        if (parentScreen !is HomeScreen) return
        val viewModel = parentScreen.getViewModel<Page2ViewModel>()
        val uiState = viewModel.collectAsState().value

        ProfileScreen(uiState, viewModel::eventDispatcher)
    }

    @Composable
    fun ProfileScreen(
        uiState: Page2Contract.UiState,
        eventDispatcher: (Page2Contract.Intent) -> Unit,
    ) {
        var dialogState by remember { mutableStateOf(false) }
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_profile),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                        .clickable {
                            eventDispatcher.invoke(Page2Contract.Intent.YourCourses)
                        }) {
                    Text(
                        text = "Your Courses",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                        .clickable {
//                    eventDispatcher.invoke(Page2Contract.Intent.OpenSavedScreen)
                        }) {
                    Text(
                        text = "Saved",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                        .clickable {
                            eventDispatcher.invoke(Page2Contract.Intent.Payment)
                        }) {
                    Text(
                        text = "Payment",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Log out",
                    modifier = Modifier
                        .padding(24.dp)
                        .clickable {
                            dialogState = true
                            //eventDispatcher.invoke(Page2Contract.Intent.LogOut)
                        },
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )

            }
            if (dialogState) {
                AlertDialog(
                    onDismissRequest = { dialogState = false },
                    title = { Text(text = "Log out") },
                    text = { Text(text = "Are you sure you want to log out?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                eventDispatcher(Page2Contract.Intent.LogOut)
                                dialogState = false
                            },
                            modifier = Modifier.padding(vertical = 8.dp),
                        ) {
                            Text(text = "OK")
                        }
                    }
                )
            }
        }
    }

    @Preview
    @Composable
    fun prevProfile() {
        StarterProjectMVITheme {
            ProfileScreen(uiState = Page2Contract.UiState.Loading) {

            }
        }
    }

}