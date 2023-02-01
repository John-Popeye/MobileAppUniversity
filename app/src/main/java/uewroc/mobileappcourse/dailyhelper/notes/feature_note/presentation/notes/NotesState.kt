package uewroc.mobileappcourse.dailyhelper.notes.feature_note.presentation.notes

import uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.model.Note
import uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.util.NoteOrder
import uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
