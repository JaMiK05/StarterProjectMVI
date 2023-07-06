package uz.gita.starterprojectmvi.presentation.screen.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.presentation.components.CourseItemInSearch

class SearchScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SearchContract.ViewModel = getViewModel<SearchViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        SearchScreenContent(uiState, viewModel::eventDispatcher)
    }

    @SuppressLint("LogNotTimber")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SearchScreenContent(
        uiState: State<SearchContract.UiState>,
        onEventDispatcher: (SearchContract.Intent) -> Unit,
    ) {

        var search by remember { mutableStateOf("") }


        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onEventDispatcher(SearchContract.Intent.BackToHome) },
                        modifier = Modifier.size(50.dp)
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


                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                            .clip(CircleShape)


                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = search,
                            onValueChange = {
                                search = it
                                onEventDispatcher(SearchContract.Intent.Search(search))
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                cursorColor = Color(0xFF050505)
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "",
                                )
                            },
                            placeholder = { Text(text = "Search course", color = Color.Black) }
                        )
                    }
                }

                if (uiState.value.courses.isNotEmpty()) {
                    Log.d("SSS", uiState.value.courses.size.toString())
                    var count = 0
                    uiState.value.courses.forEach{
                        count += it.courses.size
                    }
                    Text(

                        text = "${count} result${
                            if (count == 1) ""
                            else "s"
                        }"
                    )
                    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                        items(uiState.value.courses) { categoryData ->
                            categoryData.courses.forEach { courseData ->
                                CourseItemInSearch(
                                    courseData = courseData,
                                    onEventDispatcher = onEventDispatcher
                                )
                            }
                        }
                    }
                }else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_course),
                            modifier = Modifier
                                .size(350.dp)
                                .padding(top = 100.dp),
                            contentDescription = null
                        )
                        Text(
                            text = "Course not found",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Try searching the course with \n" +
                                    "a different keyword", textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}