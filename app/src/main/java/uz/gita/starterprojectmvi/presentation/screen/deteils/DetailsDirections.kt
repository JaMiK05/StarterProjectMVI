package uz.gita.starterprojectmvi.presentation.screen.deteils

import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

class DetailsDirections @Inject constructor(private val navigator: AppNavigator) :
    DetailsContract.Directions {
    override suspend fun back() {
        navigator.back()
    }

}