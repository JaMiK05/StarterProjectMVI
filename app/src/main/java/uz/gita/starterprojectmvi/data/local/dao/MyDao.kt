package uz.gita.starterprojectmvi.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.starterprojectmvi.data.local.entity.SimpleEntity

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCourse(courseEntity: SimpleEntity)

    @Delete
    fun deleteCource(courseEntity: SimpleEntity)

    @Query("SELECT * FROM courses")
    fun getAllCourses(): Flow<List<SimpleEntity>>
}