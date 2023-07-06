package uz.gita.starterprojectmvi.presentation.screen.home.page3

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.starterprojectmvi.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class Page3ViewModel @Inject constructor(
    private val preferences: SharedPreferences,
    private val useCase: UseCase,
    private val direction: Page3Direction,
) : Page3Contract.Model, ViewModel() {
    override fun eventDispatcher(intent: Page3Contract.Intent) {
        when (intent) {
            Page3Contract.Intent.LoadUserInfo -> {
                intent {
                    reduce {
                        Page3Contract.UiState.loadInfo(
                            preferences.getString("name", "Ghost").toString(),
                            preferences.getString("EMAIL", "Email").toString(),
                            preferences.getString("PASSWORD", "123456").toString()
                        )
                    }
                }
            }
        }
    }

    override val container =
        container<Page3Contract.UiState, Page3Contract.SideEffect>(Page3Contract.UiState.Loading)
}