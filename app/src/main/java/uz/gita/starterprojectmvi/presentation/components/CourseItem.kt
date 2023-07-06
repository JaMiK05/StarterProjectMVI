package uz.gita.starterprojectmvi.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.data.model.CourseData
import uz.gita.starterprojectmvi.presentation.screen.home.page1.Page1Contract
import uz.gita.starterprojectmvi.presentation.screen.search.SearchContract

@Composable
fun CourseItemInHome(courseData: CourseData, onEventDispatcher: (Page1Contract.Intent) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 4.dp)
            .height(350.dp)
            .border(1.dp, Color(0xFF858282), shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onEventDispatcher(Page1Contract.Intent.OpenDetailsScreen(courseData)) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(0.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(courseData.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.html4),
                    error = painterResource(id = R.drawable.html),
                    contentDescription = null,
                    contentScale = ContentScale.Crop

                )


                Button(
                    onClick = { }, modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 4.dp)
                ) {
                    Text(text = "$" + courseData.price)
                }
            }

            Text(
                text = courseData.duration,
                color = Color.Green,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = courseData.name,
                fontSize = 28.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(0.dp)
                    .weight(1f)
            )
            Text(
                text = courseData.title,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
            )
        }
    }
}

@Composable
fun CourseItemInSearch(courseData: CourseData, onEventDispatcher: (SearchContract.Intent) -> Unit) {
    Surface(
        modifier = Modifier

            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 4.dp)
            .height(350.dp)
            .border(1.dp, Color(0xFF858282), shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onEventDispatcher(SearchContract.Intent.OpenDetailsScreen(courseData)) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(0.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(courseData.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.html4),
                    error = painterResource(id = R.drawable.html),
                    contentDescription = null,
                    contentScale = ContentScale.Crop

                )


                Button(
                    onClick = { }, modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 4.dp)
                ) {
                    Text(text = courseData.price)
                }
            }

            Text(
                text = courseData.duration,
                color = Color.Green,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = courseData.name,
                fontSize = 28.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(0.dp)
                    .weight(1f)
            )
            Text(
                text = courseData.title,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
            )
        }
    }
}
