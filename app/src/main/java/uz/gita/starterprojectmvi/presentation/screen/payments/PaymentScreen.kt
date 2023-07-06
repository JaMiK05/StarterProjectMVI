package uz.gita.starterprojectmvi.presentation.screen.payments

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.presentation.components.PaymentItem

/**
 *   Created by Jamik on 7/3/2023 ot 11:02 PM
 **/
class PaymentScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: PaymentContract.ViewModel = getViewModel<PaymentViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        YourContent(uiState = uiState, onEventDispatcher = viewModel::onEvenDispatccher)
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    private fun YourContent(
        uiState: State<PaymentContract.UiState>,
        onEventDispatcher: (PaymentContract.Intent) -> Unit,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    onClick = { onEventDispatcher(PaymentContract.Intent.Back) },
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
                    text = "Payment",
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
                        PaymentItem(
                            course = course,
                            onClick = {
                                onEventDispatcher(PaymentContract.Intent.DetelScreen(course))
                            },
                            delete = {
                                onEventDispatcher(PaymentContract.Intent.Delete(course = course))
                            },
                            add = {
                                onEventDispatcher(PaymentContract.Intent.Buy(course))
                            }
                        )
                    }
                }
            }
        }
    }


}