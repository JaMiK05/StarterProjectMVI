package uz.gita.starterprojectmvi.presentation.screen.payments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.starterprojectmvi.data.local.entity.SimpleEntity
import uz.gita.starterprojectmvi.domain.repository.AppRepository
import javax.inject.*

/**
 *   Created by Jamik on 7/3/2023 ot 11:05 PM
 **/
@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val direction: PaymentDirection,
    private val repository: AppRepository,
) : ViewModel(), PaymentContract.ViewModel {
    override val uiState = MutableStateFlow(PaymentContract.UiState())

    init {
        repository.getNotBuyCourses().onEach { result ->
            result.onSuccess { list ->
                uiState.update { it.copy(list = list) }
            }
        }.launchIn(viewModelScope)
    }

    override fun onEvenDispatccher(intent: PaymentContract.Intent) {
        when (intent) {
            is PaymentContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is PaymentContract.Intent.DetelScreen -> {
                viewModelScope.launch {
                    direction.detelScreen(intent.course)
                }
            }

            is PaymentContract.Intent.Buy -> {
                val dat = intent.course
                val simlp = dat.toCourseEntity()
                simlp.buy = true
                repository.addCourseEntity(simlp)
            }

            is PaymentContract.Intent.Delete -> {
                val dat = intent.course
                val simlp = dat.toCourseEntity()
                repository.deleteCourseEntity(simlp)
            }
        }
    }
}