package uz.gita.starterprojectmvi.presentation.screen.auth.login

import uz.gita.starterprojectmvi.presentation.screen.auth.register.RegisterScreen
import uz.gita.starterprojectmvi.presentation.screen.home.HomeScreen
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

interface LoginDirection {
    suspend fun openHomeScreen()
    suspend fun openRegisterScreen()
}
class LoginDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): LoginDirection {
    override suspend fun openHomeScreen() {
        navigator.navigateTo(HomeScreen())
    }

    override suspend fun openRegisterScreen() {
        navigator.navigateTo(RegisterScreen())
    }


}