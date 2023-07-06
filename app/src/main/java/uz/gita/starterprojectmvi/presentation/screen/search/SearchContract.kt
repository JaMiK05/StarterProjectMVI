package uz.gita.starterprojectmvi.presentation.screen.search

import kotlinx.coroutines.flow.StateFlow
import uz.gita.starterprojectmvi.data.model.*

interface SearchContract {
    sealed interface Intent {
        object Load : Intent
        data class Search(val search: String) : Intent
        data class OpenDetailsScreen(val courseData: CourseData) : Intent
        object BackToHome : Intent
    }

    data class UiState(
        val courses: List<CategoryData> = emptyList(),
    )

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun eventDispatcher(intent: Intent)
    }

    interface Directions {
        suspend fun openDetailsScreen(courseData: CourseData)
        suspend fun backToHome()
    }
}