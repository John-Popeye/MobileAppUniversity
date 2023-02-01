package uewroc.mobileappcourse.dailyhelper.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uewroc.mobileappcourse.dailyhelper.todo.data.Task
import uewroc.mobileappcourse.dailyhelper.todo.data.TaskRepository
import uewroc.mobileappcourse.dailyhelper.todo.ui.AddEditTaskEvent
import uewroc.mobileappcourse.dailyhelper.todo.ui.UiEvent
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel@Inject constructor(
    private val taskRepository: TaskRepository,
    savedStateHandle: SavedStateHandle): ViewModel(){

        var task by mutableStateOf<Task?>(null)
            private set

        var description by mutableStateOf("")
            private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val taskId = savedStateHandle.get<Int>("taskId")!!
        if(taskId != -1){
            viewModelScope.launch {
                taskRepository.getTaskById(taskId)?.let { task ->
                    description = task.description
                    this@AddEditTaskViewModel.task = task
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent){
        when(event){
            is AddEditTaskEvent.OnDescriptionChange -> {
                description = event.description
            }
            AddEditTaskEvent.OnSaveTaskClick -> {
                viewModelScope.launch {
                    if(description.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "Title cannot be empty"
                        ))
                        return@launch
                    }
                    taskRepository.insertTask(
                        Task(
                            description = description,
                            isDone = task?.isDone ?: false,
                            id = task?.id
                        )
                    )
                    sendUiEvent(UiEvent.goBack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}