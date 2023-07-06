package uz.gita.starterprojectmvi.presentation.screen.yourcourses

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.flow.*
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.presentation.components.YourCorseItem

/**
 *   Created by Jamik on 7/3/2023 ot 6:56 PM
 **/
class YourCoursesScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: YourCoursesContract.ViewModel = getViewModel<YourCoursesViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        YourContent(uiState = uiState, viewModel::onEvenDispatccher)
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    private fun YourContent(
        uiState: State<YourCoursesContract.UiState>,
        onEventDispatcher: (YourCoursesContract.Intent) -> Unit,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    onClick = { onEventDispatcher(YourCoursesContract.Intent.Back) },
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(90f)
                            .size(50.dp)
                            .border(
                                1.dp,
                                shape = RoundedCornerShape(50.dp),
                                color = Color.LightGray
                            )
                            .clip(RoundedCornerShape(50))

                    )
                }

                Text(
                    text = "Your Course",
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )

            }
            if (uiState.value.list.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.emptybox),
                        contentDescription = "Saqlangan kitoblar yoq"
                    )
                }
            } else {

                LazyColumn {
                    items(uiState.value.list) { course ->
                        YourCorseItem(courseData = course, onEventDispatcher = onEventDispatcher)
                    }
                }
            }
        }
    }
}


