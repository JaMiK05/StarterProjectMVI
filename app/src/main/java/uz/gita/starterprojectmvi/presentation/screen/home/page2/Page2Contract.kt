package uz.gita.starterprojectmvi.presentation.screen.home.page2

import org.orbitmvi.orbit.ContainerHost

interface Page2Contract {
    sealed interface Model : ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Loading : UiState
        object Error : UiState
    }

    sealed interface SideEffect {
        data class ShowToast(val message: String) : SideEffect

    }

    sealed interface Intent {
        object YourCourses : Intent
        object LogOut : Intent
        object Payment : Intent
    }
}