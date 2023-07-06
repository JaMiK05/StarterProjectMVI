package uz.gita.starterprojectmvi.presentation.screen.home.page1

import kotlinx.coroutines.flow.StateFlow
import org.orbitmvi.orbit.ContainerHost
import uz.gita.starterprojectmvi.data.model.*

interface Page1Contract {
    sealed interface Model : ContainerHost<UiState, SideEffect> {
        val uiState: StateFlow<UiState>
        fun eventDispatcher(intent: Intent)
    }

    data class UiState(
        val courses: List<CategoryData> = emptyList(),
        val categories: List<String> = emptyList()
    )
    /*sealed interface UiState {
        object Loading : UiState
        object Error : UiState
    }*/

    sealed interface SideEffect {
        data class ShowToast(val message: String) : SideEffect
    }

    sealed interface Intent {
        object Load : Intent
        data class Search(val search: String) : Intent
        data class SelectCategories(val list: List<String>) : Intent
        data class OpenDetailsScreen(val coursesData: CourseData) : Intent
        object OpenSearchScreen:Intent
    }

    interface Page1Direction {
        suspend fun openDetailsScreen(courseData: CourseData)
        suspend fun openSearchScreen()
    }
}