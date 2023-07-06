package uz.gita.starterprojectmvi.domain.usecase

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.gita.starterprojectmvi.data.model.*
import uz.gita.starterprojectmvi.domain.repository.AppRepository
import javax.inject.Inject

class UseCaseImpl @Inject constructor(
    private val repository: AppRepository,
) : UseCase {
    @SuppressLint("LogNotTimber")
    override fun getCoursesByCategory(
        name: String,
        lit: List<String>,
    ): Flow<Result<List<CategoryData>>> =
        flow<Result<List<CategoryData>>> {

            val result = repository.getAllCourses()
            result.onSuccess { list ->
                Log.d("TTT", "$list")
                val resultList = arrayListOf<CategoryData>()
                if (lit.isEmpty()) {
                    list.forEach {
                        val foodList = arrayListOf<CourseData>()
                        it.courses.forEach { foodData ->
                            if (foodData.name.contains(name)) {
                                foodList.add(foodData)
                            }
                        }
                        if (foodList.isNotEmpty())
                            resultList.add(CategoryData(it.name, foodList))
                    }
                    emit(Result.success(resultList))
                } else {
                    list.forEach {
                        if (lit.contains(it.name)) {
                            val foodList = arrayListOf<CourseData>()
                            it.courses.forEach { foodData ->
                                if (foodData.name.contains(name)) {
                                    foodList.add(foodData)
                                }
                            }
                            if (foodList.isNotEmpty())
                                resultList.add(CategoryData(it.name, foodList))
                        }
                    }
                    emit(Result.success(resultList))
                    /*emit(Result.success(list.filter {
                        lit.contains(it.name)
                    }))*/
                }
            }
            result.onFailure {

                emit(Result.failure(it))
            }

        }.flowOn(Dispatchers.IO)

    override fun getCategories(): Flow<Result<List<String>>> = flow<Result<List<String>>> {
        val resultList = arrayListOf<String>()
        repository.getAllCourses()
            .onSuccess { result ->
                result.forEach {
                    resultList.add(it.name)
                }
            }
            .onFailure {
                emit(Result.failure(it))
            }
        emit(Result.success(resultList))
    }.flowOn(Dispatchers.IO)

    override fun addCourse(courseData: CourseData) = repository.addCourse(courseData)

}