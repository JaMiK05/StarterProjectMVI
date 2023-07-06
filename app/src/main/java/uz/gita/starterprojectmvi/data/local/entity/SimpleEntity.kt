package uz.gita.starterprojectmvi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.starterprojectmvi.data.model.CourseData

@Entity(tableName = "courses")
data class SimpleEntity(
   // @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @PrimaryKey
    val name: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val duration: String,
    val price: Int,
    var buy: Boolean = false,
) {
    fun entityToData() = CourseData(
        id = id,
        name = name,
        title = title,
        description = description,
        imageUrl = imageUrl,
        duration = duration,
        price = price.toString(),
    )
}
