package uz.gita.starterprojectmvi.presentation.screen.startviewpager.intro

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *   Created by Jamik on 7/3/2023 ot 3:12 PM
 **/

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val direction: IntroDirection,
) : ViewModel(), IntroContract.ViewModel {

    @Inject
    lateinit var shared: SharedPreferences

    override fun onEvenDispatcher(intent: IntroContract.Intent) {
        when (intent) {
            is IntroContract.Intent.NavigateToRegistr -> {
                shared.edit().putBoolean("intro", false).apply()
                viewModelScope.launch {
                    direction.login()
                }
            }
        }
    }
}