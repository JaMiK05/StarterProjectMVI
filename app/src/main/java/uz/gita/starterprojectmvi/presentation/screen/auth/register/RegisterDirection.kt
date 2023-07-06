package uz.gita.starterprojectmvi.presentation.screen.auth.register

import uz.gita.starterprojectmvi.presentation.screen.home.HomeScreen
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

interface RegisterDirection {
    suspend fun openMainScreen()
    suspend fun popBackStack()
}
class RegisterDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): RegisterDirection {
    override suspend fun openMainScreen() {
        navigator.replace(HomeScreen())
    }

    override suspend fun popBackStack() {
        navigator.back()
    }
}