package uz.gita.starterprojectmvi.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.starterprojectmvi.data.model.CategoryData
import uz.gita.starterprojectmvi.data.model.CourseData

interface UseCase {
    fun getCoursesByCategory(name: String, lit: List<String>): Flow<Result<List<CategoryData>>>
    fun getCategories(): Flow<Result<List<String>>>
    fun addCourse(courseData: CourseData)
}