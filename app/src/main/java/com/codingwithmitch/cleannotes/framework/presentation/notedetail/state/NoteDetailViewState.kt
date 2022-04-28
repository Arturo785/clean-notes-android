package com.codingwithmitch.cleannotes.framework.presentation.notedetail.state

import android.os.Parcelable
import com.codingwithmitch.cleannotes.business.domain.model.Note
import com.codingwithmitch.cleannotes.business.domain.state.ViewState
import kotlinx.android.parcel.Parcelize


// represents the state and everything that it can hold

@Parcelize
data class NoteDetailViewState(

    var note: Note? = null,

    var isUpdatePending: Boolean? = null

) : Parcelable, ViewState
