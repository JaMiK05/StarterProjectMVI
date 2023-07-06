package uz.gita.starterprojectmvi.presentation.screen.deteils

import kotlinx.coroutines.flow.StateFlow
import uz.gita.starterprojectmvi.data.model.CourseData

interface DetailsContract {
    sealed interface Intent {
        data class Check(val courseData: CourseData) : Intent
        data class AddCourse(val courseData: CourseData) : Intent
        object BackToHome: Intent
    }

    data class UiState(
        val isSaved: Boolean = false
    )

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }
    interface Directions{
        suspend fun back()
    }
}