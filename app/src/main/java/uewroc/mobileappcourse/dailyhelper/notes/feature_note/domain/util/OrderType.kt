package uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
