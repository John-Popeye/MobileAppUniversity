package uewroc.mobileappcourse.dailyhelper.todo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import uewroc.mobileappcourse.dailyhelper.todo.AddEditTaskViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditTaskScreen (
    onBack: () -> Unit,
    viewModel: AddEditTaskViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true ){
        viewModel.uiEvent.collect {event ->
            when(event){
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                UiEvent.goBack -> onBack()
                else -> Unit
            }

        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick =  {
                viewModel.onEvent(AddEditTaskEvent.OnSaveTaskClick)
            }){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ){
        TextField(value = viewModel.description, onValueChange = {
            viewModel.onEvent(AddEditTaskEvent.OnDescriptionChange(it))
        }, placeholder = {
            Text(text = "Title")
        }, modifier = Modifier.fillMaxWidth(),
        singleLine = false, maxLines = 5)
    }
}