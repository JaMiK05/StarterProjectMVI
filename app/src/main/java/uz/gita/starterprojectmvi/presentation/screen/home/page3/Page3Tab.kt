package uz.gita.starterprojectmvi.presentation.screen.home.page3

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import uz.gita.starterprojectmvi.presentation.screen.home.page2.Page2Contract
import uz.gita.starterprojectmvi.ui.theme.StarterProjectMVITheme

class Page3Tab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Settings"
            val icon = painterResource(id = R.drawable.nav_settings)

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
        val viewModel = parentScreen.getViewModel<Page3ViewModel>()
        val uiState = viewModel.collectAsState().value

        SettingsScreen(uiState, viewModel::eventDispatcher)

    }

    @Composable
    fun SettingsScreen(
        uiState: Page3Contract.UiState,
        eventDispatcher: (Page3Contract.Intent) -> Unit
    ) {

        eventDispatcher.invoke(Page3Contract.Intent.LoadUserInfo)
        var checkedState by remember {
            mutableStateOf(false)
        }
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        when (uiState) {
            is Page3Contract.UiState.loadInfo -> {
                name = uiState.userName
                email = uiState.userEmail
                password = uiState.userPassword
            }

            else -> {}
        }

        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings_pic),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                )

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                    .clickable {
//                    eventDispatcher.invoke(Page2Contract.Intent.OpenMyCoursesScreen)
                    }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = "Notification",
                            modifier = Modifier.size(32.dp)
                        )
                        Text(
                            text = "Notifications",
                            modifier = Modifier
                                .padding(
                                    vertical = 24.dp, horizontal = 16.dp
                                ),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = checkedState,
                            onCheckedChange = { checkedState = !checkedState })
                    }
                }
                Text(
                    text = "Account information",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                    .clickable {
//                    eventDispatcher.invoke(Page2Contract.Intent.OpenMyCoursesScreen)
                    }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = "Profile",
                            modifier = Modifier.size(32.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Name",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, start = 16.dp),
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp, start = 16.dp),
                                fontSize = MaterialTheme.typography.titleSmall.fontSize
                            )
                        }
                    }
                }
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                    .clickable {
//                    eventDispatcher.invoke(Page2Contract.Intent.OpenMyCoursesScreen)
                    }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Profile",
                            modifier = Modifier.size(32.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Email",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, start = 16.dp),
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = email,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp, start = 16.dp),
                                fontSize = MaterialTheme.typography.titleSmall.fontSize
                            )
                        }
                    }
                }
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
                    .clickable {
//                    eventDispatcher.invoke(Page2Contract.Intent.OpenMyCoursesScreen)
                    }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = "Password",
                            modifier = Modifier.size(32.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Password",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, start = 16.dp),
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Changed 2 days ago",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp, start = 16.dp),
                                fontSize = MaterialTheme.typography.titleSmall.fontSize
                            )
                        }
                    }
                }
            }

        }
    }


    @Preview
    @Composable
    fun prevSettinga() {
        StarterProjectMVITheme {
            SettingsScreen(uiState = Page3Contract.UiState.Loading) {

            }
        }
    }
}