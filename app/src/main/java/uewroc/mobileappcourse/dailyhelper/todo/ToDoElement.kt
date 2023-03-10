package uewroc.mobileappcourse.dailyhelper.todo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uewroc.mobileappcourse.dailyhelper.todo.data.Task
import uewroc.mobileappcourse.dailyhelper.todo.ui.TaskEvent

@Composable
fun ToDoElement(
    task: Task,
    onEvent: (TaskEvent) -> Unit,
    modifier: Modifier = Modifier
){

    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
            ){
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = task.description,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(TaskEvent.OnDeleteTaskClick(task))
                }) {
                    Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete")
                    
                }
            }
        }

        Checkbox(
         checked= task.isDone,
         onCheckedChange = {
             isChecked -> onEvent(TaskEvent.OnDoneChange(task, isChecked))
         }
        )

    }

}