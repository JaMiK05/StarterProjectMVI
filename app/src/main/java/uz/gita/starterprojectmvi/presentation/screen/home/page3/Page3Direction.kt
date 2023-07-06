package uz.gita.starterprojectmvi.presentation.screen.home.page3

import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

interface Page3Direction {
}
class Page3DirectionImpl @Inject constructor(
    appNavigator: AppNavigator
) :Page3Direction{

}