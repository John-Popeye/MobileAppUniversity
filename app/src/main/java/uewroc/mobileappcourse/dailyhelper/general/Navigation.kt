package uewroc.mobileappcourse.dailyhelper

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