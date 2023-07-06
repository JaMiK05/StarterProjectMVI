package uz.gita.starterprojectmvi.utils.navigation

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.starterprojectmvi.utils.navigation.NavigationArg

interface NavigationHandler {
    val navigatorBuffer:SharedFlow<NavigationArg>
}