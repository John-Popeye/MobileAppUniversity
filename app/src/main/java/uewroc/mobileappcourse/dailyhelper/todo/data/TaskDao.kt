package uewroc.mobileappcourse.dailyhelper.todo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Void

    @Delete
    suspend fun deleteTask(task: Task): Void

    @Query("SELECT * FROM todo_tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Query("SELECT * FROM todo_tasks")
    fun getAllTasks(): Flow<List<Task>>

}