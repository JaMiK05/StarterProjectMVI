package uz.gita.starterprojectmvi.presentation.screen.startviewpager.intro

import uz.gita.starterprojectmvi.presentation.screen.auth.login.LoginScreen
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

/**
 *   Created by Jamik on 7/3/2023 ot 3:12 PM
 **/
interface IntroDirection {
    suspend fun login()
}

class IntroDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : IntroDirection {
    override suspend fun login() {
        navigator.replace(LoginScreen())
    }

}