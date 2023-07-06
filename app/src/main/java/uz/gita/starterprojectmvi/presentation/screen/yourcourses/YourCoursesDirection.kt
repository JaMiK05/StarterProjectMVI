package uz.gita.starterprojectmvi.presentation.screen.yourcourses

import uz.gita.starterprojectmvi.data.model.CourseData
import uz.gita.starterprojectmvi.presentation.screen.deteils.DetailsScreen
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

/**
 *   Created by Jamik on 7/3/2023 ot 7:14 PM
 **/
interface YourCoursesDirection {

    suspend fun back()
    suspend fun deteilScreen(course: CourseData)

}

class YourCoursesDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : YourCoursesDirection {
    override suspend fun back() {
        navigator.back()
    }

    override suspend fun deteilScreen(course: CourseData) {
        navigator.navigateTo(DetailsScreen(course))
    }

}