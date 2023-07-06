package uz.gita.starterprojectmvi.presentation.screen.startviewpager.splash

import uz.gita.starterprojectmvi.presentation.screen.auth.login.LoginScreen
import uz.gita.starterprojectmvi.presentation.screen.auth.register.RegisterScreen
import uz.gita.starterprojectmvi.presentation.screen.home.HomeScreen
import uz.gita.starterprojectmvi.presentation.screen.startviewpager.intro.IntroPager
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

/**
 *   Created by Jamik on 7/3/2023 ot 2:53 PM
 **/
interface SplashDirection {
    suspend fun introPage()
    suspend fun loginPage()
    suspend fun homePage()
}

class SplashDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : SplashDirection {
    override suspend fun introPage() {
        navigator.replace(IntroPager())
    }

    override suspend fun loginPage() {
        navigator.navigateTo(LoginScreen())
    }

    override suspend fun homePage() {
        navigator.replace(HomeScreen())
    }

}