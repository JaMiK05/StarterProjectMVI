package uz.gita.starterprojectmvi.presentation.screen.startviewpager.intro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.data.model.IntroPagerData
import uz.gita.starterprojectmvi.presentation.components.*

/**
 *   Created by Jamik on 7/3/2023 ot 12:14 PM
 **/
class IntroPager : AndroidScreen() {

    val introItems = listOf(
        IntroPagerData(
            R.drawable.splash_2,
            "Learn anytime \nand anywhere",
            "Quarantine is the perfect time to spend your\nday learning something new. from anywhere!"
        ),
        IntroPagerData(
            R.drawable.splash_3,
            "Find a course\nfor you",
            "Quarantine is the perfect time to spend your\nday learning something new. from anywhere!"
        ),
        IntroPagerData(
            R.drawable.splash_4,
            "Improve your skils",
            "Quarantine is the perfect time to spend your\nday learning something new. from anywhere!"
        ),
    )

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun Content() {
        val cn = LocalContext.current
        val viewModel: IntroContract.ViewModel = getViewModel<IntroViewModel>()
        var btnText by remember { mutableStateOf("Next") }
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                count = introItems.size,
                state = pagerState,
            ) { page ->

                val pagerData = introItems[page]

                IntroComponent(
                    img = pagerData.img,
                    title = pagerData.title,
                    description = pagerData.description
                )
                btnText = if (pagerState.currentPage == introItems.size - 1) "Let's Start"
                else "Next"
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                DotsIndicator(
                    totalDots = 3,
                    size = 50.dp,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = Color.Green,
                    unSelectedColor = Color.Gray
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(cn.getColor(R.color.intro_button_color))),
                    shape = ButtonDefaults.outlinedShape,
                    onClick = {
                        if (pagerState.currentPage < introItems.size - 1) {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1
                                )
                            }
                        } else {
                            viewModel.onEvenDispatcher(IntroContract.Intent.NavigateToRegistr)
                        }
                    }) {
                    Text(text = btnText)
                }
            }

        }
    }

    @Preview
    @Composable
    fun ContentSee() {
        MaterialTheme() {
            Surface(modifier = Modifier.fillMaxSize()) {
                Content()
            }
        }
    }


}