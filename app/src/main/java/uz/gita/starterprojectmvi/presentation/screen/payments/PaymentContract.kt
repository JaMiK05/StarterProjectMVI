package uz.gita.starterprojectmvi.presentation.screen.payments

import kotlinx.coroutines.flow.StateFlow
import uz.gita.starterprojectmvi.data.local.entity.SimpleEntity
import uz.gita.starterprojectmvi.data.model.CourseData

/**
 *   Created by Jamik on 7/3/2023 ot 11:03 PM
 **/
interface PaymentContract {

    data class UiState(
        val list: List<CourseData> = emptyList(),
    )

    sealed interface Intent {
        object Back : Intent
        class DetelScreen(val course: CourseData) : Intent
        class Delete(val course: CourseData) : Intent
        class Buy(val course: CourseData) : Intent
    }

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEvenDispatccher(intent: Intent)
    }
}