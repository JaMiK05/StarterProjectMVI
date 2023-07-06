package uz.gita.starterprojectmvi.presentation.screen.deteils

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.data.model.CourseData

class DetailsScreen(private val courseData: CourseData) : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: DetailsContract.ViewModel = getViewModel<DetailsViewModel>()

        val uiState = viewModel.uiState.collectAsState()


        viewModel.onEventDispatcher(DetailsContract.Intent.Check(courseData))
        DetailsScreenContent(uiState, viewModel::onEventDispatcher)


    }

    @Composable
    private fun DetailsScreenContent(
        uiState: State<DetailsContract.UiState>,
        onEventDispatcher: (DetailsContract.Intent) -> Unit,
    ) {
        val cn = LocalContext.current
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    IconButton(
                        onClick = { onEventDispatcher(DetailsContract.Intent.BackToHome) },
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

                }
                Text(
                    text = courseData.name,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(courseData.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.html4),
                    error = painterResource(id = R.drawable.html),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Button(onClick = { }, modifier = Modifier.align(Alignment.End)) {
                    Text(text = "$" + courseData.price)
                }

                Text(
                    text = "About the course",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )

                Text(text = courseData.description)

                Text(
                    text = "Duration",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                Text(text = courseData.duration)

                Spacer(
                    modifier = Modifier
                        .height(0.dp)
                        .weight(1f)
                )
                if (!uiState.value.isSaved) {
                    Button(
                        onClick = {
                            onEventDispatcher(DetailsContract.Intent.AddCourse(courseData))
                            Toast.makeText(cn, "Payment olindi", Toast.LENGTH_SHORT).show()
                                  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 8.dp)
                            .padding(bottom = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.intro_button_color)
                        )
                    ) {
                        Text(text = "Add to card")
                    }
                }
            }
        }
    }
}