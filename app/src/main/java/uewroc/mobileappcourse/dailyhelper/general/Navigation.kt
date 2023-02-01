package uewroc.mobileappcourse.dailyhelper

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import uewroc.mobileappcourse.dailyhelper.general.AppDatabase
import uewroc.mobileappcourse.dailyhelper.general.common.Screen
import uewroc.mobileappcourse.dailyhelper.todo.Routes
import uewroc.mobileappcourse.dailyhelper.todo.ToDo
import uewroc.mobileappcourse.dailyhelper.todo.ui.AddEditTaskScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =  Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainPage(navController = navController)
        }
        composable(Routes.TASK_LIST){
            ToDo(onNavigate = {navController.navigate(it.route)})
        }

            composable(route = uewroc.mobileappcourse.dailyhelper.notes.feature_note.presentation.util.Screen.NotesScreen.route) {
                uewroc.mobileappcourse.dailyhelper.notes.feature_note.presentation.notes.NotesScreen(
                    navController = navController
                )
            }
            composable(
                route = uewroc.mobileappcourse.dailyhelper.notes.feature_note.presentation.util.Screen.AddEditNoteScreen.route +
                        "?noteId={noteId}&noteColor={noteColor}",
                arguments = kotlin.collections.listOf(
                    androidx.navigation.navArgument(
                        name = "noteId"
                    ) {
                        type = androidx.navigation.NavType.IntType
                        defaultValue = -1
                    },
                    androidx.navigation.navArgument(
                        name = "noteColor"
                    ) {
                        type = androidx.navigation.NavType.IntType
                        defaultValue = -1
                    },
                )
            ) {
                val color = it.arguments?.getInt("noteColor") ?: -1
                uewroc.mobileappcourse.dailyhelper.notes.feature_note.presentation.add_edit_note.AddEditNoteScreen(
                    navController = navController,
                    noteColor = color
                )
            }


        composable(Routes.ADD_EDIT_TASK + "?taskId={taskId",
            arguments = listOf(
                navArgument(name="taskId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )){
            AddEditTaskScreen(onBack = {
                navController.popBackStack()
            })
        }
    }
}