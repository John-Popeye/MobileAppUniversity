package uewroc.mobileappcourse.dailyhelper.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class], version = 1
)
abstract class TaskDatabase: RoomDatabase(){

    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "task_db"
    }
}