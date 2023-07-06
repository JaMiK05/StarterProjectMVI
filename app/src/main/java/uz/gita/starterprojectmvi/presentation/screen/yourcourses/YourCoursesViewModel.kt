package uz.gita.starterprojectmvi.presentation.screen.yourcourses

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.starterprojectmvi.domain.repository.AppRepository
import javax.inject.Inject

/**
 *   Created by Jamik on 7/3/2023 ot 7:07 PM
 **/
@SuppressLint("LogNotTimber")
@HiltViewModel
class YourCoursesViewModel @Inject constructor(
    private val direction: YourCoursesDirection,
    private val repository: AppRepository,
) : ViewModel(), YourCoursesContract.ViewModel {

    init {
        viewModelScope.launch {
            repository.getMyCourses().onEach { result ->
                result.onSuccess { dataList ->
                    uiState.update { it.copy(list = dataList) }
                }
                result.onFailure {
                    Log.d("TTT", "iwlamadi")
                }
            }.launchIn(viewModelScope)
        }
    }

    override val uiState = MutableStateFlow(YourCoursesContract.UiState())
    override fun onEvenDispatccher(intent: YourCoursesContract.Intent) {
        when (intent) {
            is YourCoursesContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is YourCoursesContract.Intent.DetelScreen -> {
                viewModelScope.launch {
                    direction.deteilScreen(intent.course)
                }
            }
        }
    }

}