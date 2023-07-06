package uz.gita.starterprojectmvi.presentation.screen.home.page2

import android.content.SharedPreferences
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.starterprojectmvi.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class Page2ViewModel @Inject constructor(
    private val useCase: UseCase,
    private val direction: Page2Direction,
) : Page2Contract.Model, ViewModel() {

    @Inject
    lateinit var shared: SharedPreferences

    override fun eventDispatcher(intent: Page2Contract.Intent) {
        when (intent) {
            is Page2Contract.Intent.YourCourses -> {
                viewModelScope.launch {
                    direction.yourCourses()
                }
            }

            is Page2Contract.Intent.LogOut -> {
                shared.edit().putBoolean("HAS_USER", true).apply()
                viewModelScope.launch {
                    direction.login()
                }
            }

            is Page2Contract.Intent.Payment -> {
                viewModelScope.launch {
                    direction.payment()
                }
            }
        }
    }

    override val container =
        container<Page2Contract.UiState, Page2Contract.SideEffect>(Page2Contract.UiState.Loading)
}