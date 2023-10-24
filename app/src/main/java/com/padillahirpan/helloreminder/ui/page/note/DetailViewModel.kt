package com.padillahirpan.helloreminder.ui.page.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padillahirpan.helloreminder.ui.data.ItemNote
import com.padillahirpan.helloreminder.ui.data.NoteType
import com.padillahirpan.helloreminder.ui.data.db.NoteEntity
import com.padillahirpan.helloreminder.ui.data.repository.LocalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */
class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val localRepository: LocalRepository
): ViewModel() {
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    /**
     * Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ItemDetailsUiState> =
        localRepository.getItemStream(itemId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(itemDetails = it.toItemNoteDetail())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    /**
     * Reduces the item quantity by one and update the [ItemsRepository]'s data source.
     */
//    fun reduceQuantityByOne() {
//        viewModelScope.launch {
//            val currentItem = uiState.value.itemDetails.toNoteEntity()
//            if (currentItem.quantity > 0) {
//                localRepository.updateItem(currentItem.copy(quantity = currentItem.quantity - 1))
//            }
//        }
//    }

    /**
     * Deletes the item from the [ItemsRepository]'s data source.
     */
    suspend fun deleteItem() {
        localRepository.deleteItem(uiState.value.itemDetails.toNoteEntity())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


/**
 * UI state for ItemDetailsScreen
 */
data class ItemDetailsUiState(
    val itemDetails: ItemNote = ItemNote()
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun NoteEntity.toItemNoteDetail(): ItemNote = ItemNote(
    id = id,
    name = name,
    description = description,
    type = when(type) {
        NoteType.IN_PLAN.id -> NoteType.IN_PLAN.description
        NoteType.ON_GOING.id -> NoteType.ON_GOING.description
        NoteType.COMPLETED.id -> NoteType.COMPLETED.description
        else -> ""
    }
)

fun ItemNote.toNoteEntity(): NoteEntity = NoteEntity(
    id = id,
    name = name,
    description = description,
    type = when(type) {
        NoteType.IN_PLAN.description -> NoteType.IN_PLAN.id
        NoteType.ON_GOING.description -> NoteType.ON_GOING.id
        NoteType.COMPLETED.description -> NoteType.COMPLETED.id
        else -> NoteType.IN_PLAN.id
    }
)