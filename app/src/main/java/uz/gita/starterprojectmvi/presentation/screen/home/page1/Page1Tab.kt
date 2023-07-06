package uz.gita.starterprojectmvi.presentation.screen.home.page1

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.*
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.presentation.components.CourseItemInHome
import uz.gita.starterprojectmvi.presentation.screen.home.HomeScreen
import uz.gita.starterprojectmvi.ui.theme.MyColor1

class Page1Tab : Tab, AndroidScreen() {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Home"
            val icon = painterResource(id = R.drawable.nav_course)

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
        val context = LocalContext.current
        val viewModel = getViewModel<Page1ViewModel>()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is Page1Contract.SideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
        val uiState = viewModel.uiState.collectAsState().value

        HomeScreenContent(uiState, viewModel::eventDispatcher)
    }

    @SuppressLint("MutableCollectionMutableState")
    @Composable
    private fun HomeScreenContent(
        uiState: Page1Contract.UiState,
        onEventDispatcher: (Page1Contract.Intent) -> Unit,
    ) {
        val selectedCategories by remember {
            mutableStateOf(arrayListOf<String>())
        }
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .clickable {
                            onEventDispatcher(Page1Contract.Intent.OpenSearchScreen)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Search course", modifier = Modifier.padding(start = 8.dp))

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                if (uiState.categories.isEmpty() || uiState.courses.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            strokeWidth = 7.dp,
                            color = MyColor1
                        )
                    }
                } else {

                    LazyRow(modifier = Modifier.padding(vertical = 16.dp)) {
                        items(uiState.categories.size) {
                            Button(
                                onClick = {
                                    if (selectedCategories.contains(uiState.categories[it])) {
                                        selectedCategories.remove(uiState.categories[it])
                                    } else {
                                        selectedCategories.add(uiState.categories[it])
                                    }

                                    onEventDispatcher(
                                        Page1Contract.Intent.SelectCategories(selectedCategories)
                                    )
                                },
                                modifier = Modifier.padding(horizontal = 8.dp),

                                colors = if (selectedCategories.contains(uiState.categories[it])) {
                                    ButtonDefaults.buttonColors(Color.Blue)
                                } else
                                    ButtonDefaults.buttonColors(Color(0xFF9E9B9B))
                            ) {
                                Text(text = uiState.categories[it])
                            }
                        }
                    }

                    LazyColumn {
                        items(uiState.courses) { categoryData ->
                            Column {
                                categoryData.courses.forEach { courseData ->
                                    CourseItemInHome(courseData, onEventDispatcher)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}