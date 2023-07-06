package uz.gita.starterprojectmvi.presentation.screen.auth.login

import org.orbitmvi.orbit.ContainerHost

interface LoginContract {
    interface Model:ContainerHost<UiState, SideEffect>{
        fun eventDispatcher(intent: Intent)
    }
    sealed interface UiState{
        object Welcome: UiState
        object Loading: UiState

    }
    sealed interface SideEffect{
        data class ErrorInternetConnection(val message:String): SideEffect
        data class ErrorWrongLoginOrPassword(val message:String): SideEffect
    }

    sealed interface Intent{
        data class LoginUser(val email:String, val password:String): Intent
        object OpenSignUp: Intent
    }
}