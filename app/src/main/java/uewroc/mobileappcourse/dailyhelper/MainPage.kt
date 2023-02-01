package uewroc.mobileappcourse.dailyhelper

import android.content.Intent
import androidx.annotation.ContentView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import uewroc.mobileappcourse.dailyhelper.calculator.CalculatorActivity
import uewroc.mobileappcourse.dailyhelper.general.common.Screen
import uewroc.mobileappcourse.dailyhelper.todo.Routes
import uewroc.mobileappcourse.dailyhelper.ui.theme.Basic_green
import uewroc.mobileappcourse.dailyhelper.ui.theme.Basic_offwhite
import uewroc.mobileappcourse.dailyhelper.ui.theme.Basic_teal

@Composable
fun MainPage(navController: NavController) {
    var context = LocalContext.current
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .clickable {
                    val intent = Intent(context, CalculatorActivity::class.java)
                    startActivity(context, intent, null)
                }
        ) {
            Column(Modifier.background(Basic_green)) {
                Text(
                    text = "Calculator", modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center, fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_calculate_24),
                    contentDescription = stringResource(R.string.calculator_description),
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxSize()
                )

            }


        }

        Card(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .clickable {
                    navController.navigate(uewroc.mobileappcourse.dailyhelper.notes.feature_note.presentation.util.Screen.NotesScreen.route)
                }
        ) {
            Column(Modifier.background(Basic_offwhite)) {

                Text(
                    text = "Notes", modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center, fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_edit_note_24),
                    contentDescription = stringResource(R.string.notes_description),
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxSize()
                )

            }
        }
        Card(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .clickable {
                    navController.navigate(Routes.TASK_LIST)
                }
        ) {
            Column(Modifier.background(Basic_teal)) {
                Text(
                    text = "To Do", modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center, fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_checklist_24),
                    contentDescription = stringResource(R.string.todo_description),
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxSize()
                )
            }


        }
    }

}