package uz.gita.starterprojectmvi.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.starterprojectmvi.data.local.entity.SimpleEntity
import uz.gita.starterprojectmvi.data.model.CategoryData
import uz.gita.starterprojectmvi.data.model.CourseData

interface AppRepository {
    suspend fun getAllCourses(): Result<List<CategoryData>>
    suspend fun getMyCourses(): Flow<Result<List<CourseData>>>
    fun getNotBuyCourses(): Flow<Result<List<CourseData>>>
    fun addCourseEntity(cours: SimpleEntity)
    fun deleteCourseEntity(cours: SimpleEntity)
    fun addCourse(courseData: CourseData)
}