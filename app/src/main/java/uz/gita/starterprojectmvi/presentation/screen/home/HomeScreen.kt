package uz.gita.starterprojectmvi.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.*
import org.orbitmvi.orbit.compose.*
import uz.gita.starterprojectmvi.presentation.screen.home.page1.*
import uz.gita.starterprojectmvi.presentation.screen.home.page2.*
import uz.gita.starterprojectmvi.presentation.screen.home.page3.*
import uz.gita.starterprojectmvi.utils.navigation.MyScreen
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.ui.theme.MyColor1

class HomeScreen : MyScreen() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewmodel = getViewModel<HomeViewModel>()
        val uiState = viewmodel.collectAsState().value

        TabNavigator(tab = Page2Tab()) { tab ->
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(tab = Page1Tab()) {
                            viewmodel.eventDispatcher(HomeContract.Intent.SlideCourses)
                        }
                        TabNavigationItem(tab = Page2Tab()) {
                            viewmodel.eventDispatcher(HomeContract.Intent.SlideProfile)
                        }
                        TabNavigationItem(tab = Page3Tab()) {
                            viewmodel.eventDispatcher(HomeContract.Intent.SlideSettins)
                        }
                    }
                },
                topBar = {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                when (uiState) {
                                    HomeContract.UiState.TabCourses -> {
                                        Column {
                                            Text(
                                                text = "Hello,",
                                                fontSize = MaterialTheme.typography.titleSmall.fontSize
                                            )
                                            Text(
                                                text = viewmodel.getName(),
                                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.weight(1f))
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_notif),
                                            contentDescription = "Notification",
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(4.dp)
                                        )
                                    }

                                    HomeContract.UiState.TabProfile -> {
                                        Text(
                                            text = "Profile",
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                                        )
                                    }

                                    HomeContract.UiState.TabSettins -> {
                                        Text(
                                            text = "Settings",
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                                        )

                                    }

                                    else -> {}
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                content = { paddingValues ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        CurrentTab()
                    }

                }
            )
        }
        viewmodel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                else -> {
                    Toast.makeText(context, "Fail...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @Composable
    fun RowScope.TabNavigationItem(
        tab: Tab,
        listener: () -> Unit,
    ) {
        val tabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab; listener.invoke() },
            icon = {
                Icon(
                    painter = tab.options.icon!!,
                    contentDescription = tab.options.title,
                    modifier = Modifier.size(50.dp),
                    tint = if (tabNavigator.current == tab) MyColor1 else Color.Black
                )
            }
        )
    }
}