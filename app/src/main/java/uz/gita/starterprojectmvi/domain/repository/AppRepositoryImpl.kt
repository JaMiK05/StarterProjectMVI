package uz.gita.starterprojectmvi.domain.repository

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import uz.gita.starterprojectmvi.data.local.dao.MyDao
import uz.gita.starterprojectmvi.data.local.entity.SimpleEntity
import uz.gita.starterprojectmvi.data.model.*
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val dao: MyDao,
) : AppRepository {
    private val db = Firebase.firestore
    private val scope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    @SuppressLint("LogNotTimber")
    override suspend fun getAllCourses(): Result<List<CategoryData>> = withContext(Dispatchers.IO) {
        try {
            val a = db.collection("courses")
                .get()
                .await()

            val resultList = arrayListOf<CategoryData>()

            a.documents.forEach {
                val coursesList = arrayListOf<CourseData>()

                val subCollection = it.reference.collection("innner_courses")
                    .get()
                    .await()

                subCollection.forEach { course ->
                    val pricee = course.get("price") as String
                    coursesList.add(
                        CourseData(
                            id = course.get("id") as Long,
                            name = course.get("name") as String,
                            description = course.get("description") as String,
                            duration = course.get("duration") as String,
                            imageUrl = course.get("imageUrl") as String,
                            price = pricee.substring(1),
                            title = course.get("title") as String,
                        )
                    )
                }
                resultList.add(
                    CategoryData(
                        name = it.get("name") as String,
                        courses = coursesList
                    )
                )
            }
            return@withContext Result.success(resultList)
        } catch (e: Exception) {
            Log.d("ERROR", e.message!!)
            return@withContext Result.failure(e)
        }
    }

    @SuppressLint("LogNotTimber")
    override suspend fun getMyCourses(): Flow<Result<List<CourseData>>> = callbackFlow {
        Log.d("TTT", "get course database keldi")
        dao.getAllCourses().onEach { list ->
            val res = ArrayList<CourseData>()
            list.forEach {
                if (it.buy) {
                    res.add(it.entityToData())
                }
            }
            Log.d("TTT", "${res.size}")
            trySend(Result.success(res))
        }.launchIn(scope)
        awaitClose()
    }

    @SuppressLint("LogNotTimber")
    override fun getNotBuyCourses(): Flow<Result<List<CourseData>>> = callbackFlow {
        Log.d("TTT", "get course database keldi")
        dao.getAllCourses().onEach { list ->
            val res = ArrayList<CourseData>()
            list.forEach {
                if (!it.buy) {
                    res.add(it.entityToData())
                }
            }
            Log.d("TTT", "${res.size}")
            trySend(Result.success(res))
        }.launchIn(scope)
        awaitClose()
    }

    override fun addCourseEntity(cours: SimpleEntity) {
        dao.addCourse(cours)
    }

    override fun deleteCourseEntity(cours: SimpleEntity) {
        dao.deleteCource(cours)
    }

    override fun addCourse(courseData: CourseData) {
        dao.addCourse(courseData.toCourseEntity())
    }

}