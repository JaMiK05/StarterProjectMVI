package uz.gita.starterprojectmvi.presentation.screen.search

import uz.gita.starterprojectmvi.data.model.CourseData
import uz.gita.starterprojectmvi.presentation.screen.deteils.DetailsScreen
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

class SearchDirectionsImpl @Inject constructor(private val navigator: AppNavigator) :
    SearchContract.Directions {
    override suspend fun openDetailsScreen(courseData: CourseData) {
        navigator.navigateTo(DetailsScreen(courseData))
    }

    override suspend fun backToHome() {
        navigator.back()
    }

}