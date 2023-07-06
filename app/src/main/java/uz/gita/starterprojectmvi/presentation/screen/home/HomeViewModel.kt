package uz.gita.starterprojectmvi.presentation.screen.home

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : HomeContract.Model, ViewModel() {

    @Inject
    lateinit var shared: SharedPreferences

    override fun eventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.SlideCourses -> {
                intent {
                    reduce {
                        HomeContract.UiState.TabCourses
                    }
                }

            }

            HomeContract.Intent.SlideProfile -> {
                intent {
                    reduce {
                        HomeContract.UiState.TabProfile
                    }
                }
            }

            HomeContract.Intent.SlideSettins -> {
                intent {
                    reduce {
                        HomeContract.UiState.TabSettins
                    }
                }
            }
        }
    }

    override fun getName(): String {
        return shared.getString("name", "John").toString()
    }

    override val container =
        container<HomeContract.UiState, HomeContract.SideEffect>(HomeContract.UiState.Loading)
}