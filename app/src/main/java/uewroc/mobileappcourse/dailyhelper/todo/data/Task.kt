package uewroc.mobileappcourse.dailyhelper.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name="description") val description: String,
    @ColumnInfo(name="is_done") val isDone: Boolean
    )
