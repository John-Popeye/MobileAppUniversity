package uewroc.mobileappcourse.dailyhelper.todo.ui

sealed class AddEditTaskEvent{
    data class OnDescriptionChange(val description: String): AddEditTaskEvent()
    object OnSaveTaskClick: AddEditTaskEvent()
}
