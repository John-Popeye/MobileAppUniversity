package uewroc.mobileappcourse.dailyhelper.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uewroc.mobileappcourse.dailyhelper.todo.data.Task
import uewroc.mobileappcourse.dailyhelper.todo.data.TaskRepository
import uewroc.mobileappcourse.dailyhelper.todo.ui.TaskEvent
import uewroc.mobileappcourse.dailyhelper.todo.ui.UiEvent
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel@Inject constructor(private val taskRepository: TaskRepository): ViewModel(){
    val tasks = taskRepository.getALlTasks()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTask: Task? = null

    fun onEvent(event: TaskEvent){
        when(event) {
            is TaskEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    deletedTask = event.task
                    taskRepository.deleteTask(event.task)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Task succesfully deleated",
                        action = "Undo"
                    ))
                }
            }

            is TaskEvent.OnAddTaskClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TASK))
            }
            is TaskEvent.OnDoneChange -> {
                viewModelScope.launch {
                    taskRepository.insertTask(event.task.copy(
                        isDone = event.isDone
                    ))
                }
            }
            is TaskEvent.OnTaskClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TASK+ "?todoId=${event.task.id}"))
            }
            is TaskEvent.onUndoDeleteClick -> {
                deletedTask?.let { task -> viewModelScope.launch {
                    taskRepository.insertTask(deletedTask!!)
                } }
            }
        }
    }


    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }




}

