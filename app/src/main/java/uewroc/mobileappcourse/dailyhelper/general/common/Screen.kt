package uewroc.mobileappcourse.dailyhelper.general.common

sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object ToDoScreen: Screen("todo_screen")
}
