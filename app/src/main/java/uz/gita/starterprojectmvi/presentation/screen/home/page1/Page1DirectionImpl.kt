package uz.gita.starterprojectmvi.presentation.screen.home.page1

import uz.gita.starterprojectmvi.data.model.CourseData
import uz.gita.starterprojectmvi.presentation.screen.deteils.DetailsScreen
import uz.gita.starterprojectmvi.presentation.screen.search.SearchScreen
import uz.gita.starterprojectmvi.presentation.screen.yourcourses.YourCoursesContract
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

class Page1DirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator,
) : Page1Contract.Page1Direction {
    override suspend fun openDetailsScreen(courseData: CourseData) {
        appNavigator.navigateTo(DetailsScreen(courseData))
    }

    override suspend fun openSearchScreen() {
        appNavigator.navigateTo(SearchScreen())
    }
}