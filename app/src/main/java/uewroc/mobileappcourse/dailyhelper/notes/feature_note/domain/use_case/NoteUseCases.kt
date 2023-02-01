package uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.use_case

import uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.use_case.AddNote
import uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.use_case.DeleteNote
import uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.use_case.GetNote
import uewroc.mobileappcourse.dailyhelper.notes.feature_note.domain.use_case.GetNotes

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
