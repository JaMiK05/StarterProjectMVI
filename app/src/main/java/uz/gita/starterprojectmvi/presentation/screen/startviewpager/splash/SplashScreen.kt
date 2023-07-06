package uz.gita.starterprojectmvi.presentation.screen.startviewpager.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.starterprojectmvi.R
import uz.gita.starterprojectmvi.presentation.components.IntroComponent

/**
 *   Created by Jamik on 7/3/2023 ot 2:30 PM
 **/

class SplashScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<SplashViewModel>()
        Box(modifier = Modifier.fillMaxSize()) {
            IntroComponent(img = R.drawable.splash_1, title = "CodeFactory", description = "")
        }
    }

    @Preview
    @Composable
    fun ContentSe() {
        MaterialTheme() {
            Surface(modifier = Modifier.fillMaxSize()) {
                Content()
            }
        }
    }

}