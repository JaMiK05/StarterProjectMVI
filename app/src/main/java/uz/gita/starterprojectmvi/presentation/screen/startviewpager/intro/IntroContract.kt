package uz.gita.starterprojectmvi.presentation.screen.startviewpager.intro

/**
 *   Created by Jamik on 7/3/2023 ot 3:15 PM
 **/
interface IntroContract {

    sealed interface Intent {
        object NavigateToRegistr : Intent
    }

    interface ViewModel {
        fun onEvenDispatcher(intent: Intent)
    }

}