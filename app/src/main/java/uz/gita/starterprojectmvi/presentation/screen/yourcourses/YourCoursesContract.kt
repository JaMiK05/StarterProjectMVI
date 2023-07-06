package uz.gita.starterprojectmvi.presentation.screen.yourcourses

import kotlinx.coroutines.flow.StateFlow
import uz.gita.starterprojectmvi.data.model.CourseData

/**
 *   Created by Jamik on 7/3/2023 ot 6:57 PM
 **/
interface YourCoursesContract {


    data class UiState(
        val list: List<CourseData> = emptyList(),
    )

    sealed interface Intent {
        object Back : Intent
        class DetelScreen(val course: CourseData) : Intent
    }

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEvenDispatccher(intent: Intent)
    }

}