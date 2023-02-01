package uewroc.mobileappcourse.dailyhelper.todo.ui

sealed class UiEvent {
    object goBack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}