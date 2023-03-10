package uewroc.mobileappcourse.dailyhelper.todo

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uewroc.mobileappcourse.dailyhelper.todo.ui.TaskEvent
import uewroc.mobileappcourse.dailyhelper.todo.ui.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ToDo(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ToDoViewModel = hiltViewModel()
) {
    val tasks = viewModel.tasks.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)

                }
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TaskEvent.onUndoDeleteClick)
                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TaskEvent.OnAddTaskClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(tasks.value) { task ->
                ToDoElement(task = task, onEvent = viewModel::onEvent, modifier = Modifier.fillMaxWidth().clickable {
                    viewModel.onEvent(TaskEvent.OnTaskClick(task))
                }
                    .padding(16.dp))

            }
        }
    }


}
