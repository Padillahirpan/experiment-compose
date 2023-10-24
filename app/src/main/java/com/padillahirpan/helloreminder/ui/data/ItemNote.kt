package com.padillahirpan.helloreminder.ui.data

import androidx.room.ColumnInfo

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */

enum class NoteType(val id: Int, val description: String) {
    IN_PLAN(1, "In plan"),
    ON_GOING(2, "On going"),
    COMPLETED(3, "Completed")
}

data class ItemNote(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val type: String = ""
)
