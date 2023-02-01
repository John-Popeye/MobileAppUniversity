package uewroc.mobileappcourse.dailyhelper.todo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uewroc.mobileappcourse.dailyhelper.todo.data.TaskDatabase
import uewroc.mobileappcourse.dailyhelper.todo.data.TaskRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(app, TaskDatabase::class.java, "task_db").build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepository(db.taskDao)
    }
}