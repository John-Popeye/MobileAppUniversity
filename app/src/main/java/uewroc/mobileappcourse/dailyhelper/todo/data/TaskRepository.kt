package uewroc.mobileappcourse.dailyhelper.todo.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun insertTask(task: Task){
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun getTaskById(taskId: Int): Task?{
        return taskDao.getTaskById(taskId);
    }

    fun getALlTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }
}