package uz.gita.starterprojectmvi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.starterprojectmvi.data.local.dao.MyDao
import uz.gita.starterprojectmvi.data.local.entity.SimpleEntity

@Database(entities = [SimpleEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase:RoomDatabase() {
    abstract fun getDao():MyDao
    companion object{
        val DB_NAME = "database"
    }
}