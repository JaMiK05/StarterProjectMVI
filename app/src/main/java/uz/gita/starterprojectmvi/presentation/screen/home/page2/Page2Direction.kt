package uz.gita.starterprojectmvi.presentation.screen.home.page2

import uz.gita.starterprojectmvi.presentation.screen.auth.login.LoginScreen
import uz.gita.starterprojectmvi.presentation.screen.payments.PaymentScreen
import uz.gita.starterprojectmvi.presentation.screen.yourcourses.YourCoursesScreen
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

interface Page2Direction {

    suspend fun yourCourses()
    suspend fun payment()
    suspend fun login()

}

class Page2DirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator,
) : Page2Direction {
    override suspend fun yourCourses() {
        appNavigator.navigateTo(YourCoursesScreen())
    }

    override suspend fun payment() {
        appNavigator.navigateTo(PaymentScreen())
    }

    override suspend fun login() {
        appNavigator.replace(LoginScreen())
    }

}