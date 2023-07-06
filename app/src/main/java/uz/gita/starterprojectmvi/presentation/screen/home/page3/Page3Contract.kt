package uz.gita.starterprojectmvi.presentation.screen.home.page3

import org.orbitmvi.orbit.ContainerHost

interface Page3Contract {
    sealed interface Model: ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent:Intent)
    }
    sealed interface UiState{
        object Loading:UiState
        object Error:UiState
        data class loadInfo(val userName:String, val userEmail:String, val userPassword:String):UiState
    }
    sealed interface SideEffect{
        data class ShowToast(val message:String):SideEffect

    }
    sealed interface Intent{
        object LoadUserInfo:Intent
    }
}