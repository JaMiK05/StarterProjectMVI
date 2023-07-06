package uz.gita.starterprojectmvi.presentation.screen.payments

import uz.gita.starterprojectmvi.data.model.CourseData
import uz.gita.starterprojectmvi.presentation.screen.deteils.DetailsScreen
import uz.gita.starterprojectmvi.utils.navigation.AppNavigator
import javax.inject.Inject

/**
 *   Created by Jamik on 7/3/2023 ot 11:12 PM
 **/
interface PaymentDirection {

    suspend fun back()

    suspend fun detelScreen(cours: CourseData)

}

class PaymentDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : PaymentDirection {
    override suspend fun back() {
        navigator.back()
    }

    override suspend fun detelScreen(cours: CourseData) {
        navigator.navigateTo(DetailsScreen(cours))
    }

}