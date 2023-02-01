package uewroc.mobileappcourse.dailyhelper.todo.ui

import uewroc.mobileappcourse.dailyhelper.todo.data.Task

sealed class TaskEvent {

    data class OnDeleteTaskClick(val task: Task): TaskEvent()
    data class OnDoneChange(val task: Task, val isDone: Boolean): TaskEvent()
    object onUndoDeleteClick: TaskEvent()
    data class OnTaskClick(val task: Task): TaskEvent()
    object OnAddTaskClick: TaskEvent()
}
