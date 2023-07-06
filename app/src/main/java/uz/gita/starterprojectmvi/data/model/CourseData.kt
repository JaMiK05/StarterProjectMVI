package uz.gita.starterprojectmvi.data.model

import uz.gita.starterprojectmvi.data.local.entity.SimpleEntity

data class CourseData(
    val id: Long,
    val name: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val duration: String,
    val price: String,
) {
    val idnum = "$name$id".hashCode().toLong()
    fun toCourseEntity(): SimpleEntity = SimpleEntity(
        id = idnum,
        name = name,
        title = title,
        description = description,
        imageUrl = imageUrl,
        duration = duration,
        price = Integer.parseInt(price)
    )
}
