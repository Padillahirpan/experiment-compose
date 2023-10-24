package com.padillahirpan.helloreminder.ui.page.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.padillahirpan.helloreminder.ui.data.ItemNote
import com.padillahirpan.helloreminder.ui.data.repository.LocalRepository

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */
class CreateNoteViewModel(
    private val localRepository: LocalRepository
): ViewModel() {
    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(itemDetails: ItemNote) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /**
     * Inserts an [Item] in the Room database
     */
    suspend fun saveItem() {
        if (validateInput()) {
            localRepository.insertItem(itemUiState.itemDetails.toNoteEntity())
        }
    }

    private fun validateInput(
        uiState: ItemNote = itemUiState.itemDetails
    ): Boolean {
        return with(uiState) {
            name.isNotBlank() && description.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class ItemUiState(
    val itemDetails: ItemNote = ItemNote(),
    val isEntryValid: Boolean = false
)