package uz.gita.starterprojectmvi.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.data.model.CourseData

/**
 *   Created by Jamik on 7/3/2023 ot 10:30 PM
 **/

@Composable
fun PaymentItem(
    course: CourseData,
    onClick: (course: CourseData) -> Unit,
    delete: (course: CourseData) -> Unit,
    add: (course: CourseData) -> Unit,
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
            .clickable { onClick.invoke(course) },
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(Color(context.getColor(R.color.white))),
        elevation = CardDefaults.outlinedCardElevation()
    ) {

        Row(
            modifier = Modifier
                .clickable {onClick.invoke(course) }
                .padding(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .padding(10.dp),
                colors = CardDefaults.cardColors(Color.Gray)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .padding(4.dp),
                    model = ImageRequest.Builder(LocalContext.current).data(course.imageUrl)
                        .crossfade(true).build(),
                    placeholder = painterResource(id = R.drawable.html4),
                    error = painterResource(id = R.drawable.html),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
            ) {

                Text(
                    text = course.name,
                    color = Color.Black,
                    fontSize = 14.sp,
                    maxLines = 1,
                    fontWeight = FontWeight(900),
                    modifier = Modifier
                        .padding(top = 20.dp)
                )

                Text(
                    text = course.description,
                    color = Color.DarkGray,
                    fontSize = 10.sp,
                    maxLines = 2,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    TextButton(
                        modifier = Modifier
                            .background(Color.Red)
                            .padding(horizontal = 10.dp),
                        onClick = { delete.invoke(course) },
                        shape = ShapeDefaults.ExtraLarge
                    ) {
                        Text(
                            text = "Delete",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    TextButton(
                        modifier = Modifier
                            .background(Color.Green)
                            .padding(horizontal = 10.dp),
                        onClick = { add.invoke(course) },
                        shape = ShapeDefaults.ExtraLarge
                    ) {
                        Text(
                            text = "Buy",
                            color = Color.White
                        )
                    }
                }
            }
            Text(
                text = "$" + course.price,
                color = Color.Blue,
                fontSize = 10.sp,
                fontWeight = FontWeight(700),
                modifier = Modifier.padding(top = 8.dp)
            )

        }

    }

}

@Preview
@Composable
fun PrevPaymentItem() {
    val data = CourseData(
        id = 1,
        name = "name",
        title = "title",
        description = "description",
        imageUrl = "imageUrl",
        duration = "duration",
        price = "32000",
    )
    MaterialTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.height(70.dp)
            ) {
                PaymentItem(course = data, onClick = {}, delete = {}) {}
            }
        }
    }

}