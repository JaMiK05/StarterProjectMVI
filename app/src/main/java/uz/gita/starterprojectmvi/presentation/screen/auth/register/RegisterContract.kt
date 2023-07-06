package uz.gita.starterprojectmvi.presentation.screen.auth.register

import org.orbitmvi.orbit.ContainerHost

interface RegisterContract {
    interface Model : ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Welcome : UiState
        object Loading : UiState
    }

    sealed interface Intent {
        data class CreateUser(val email: String, val password: String, val name: String) : Intent
        object Back : Intent
    }

    sealed interface SideEffect {
        data class HasError(val message: String) : SideEffect
    }

    interface SignUpDirection {
        suspend fun openMainScreen()
        suspend fun popBackStack()
    }
}