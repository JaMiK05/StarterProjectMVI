package uz.gita.starterprojectmvi.presentation.screen.startviewpager.splash

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *   Created by Jamik on 7/3/2023 ot 2:52 PM
 **/
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: SplashDirection,
) : ViewModel() {

    @Inject
    lateinit var shared: SharedPreferences

    init {
        viewModelScope.launch {
            delay(1000)
            if (shared.getBoolean("intro", true)) {
                direction.introPage()
            } else if (shared.getBoolean("HAS_USER", true)) {
                direction.loginPage()
            } else {
                direction.homePage()
            }
        }
    }

}