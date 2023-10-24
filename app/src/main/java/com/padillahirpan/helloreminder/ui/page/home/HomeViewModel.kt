package com.padillahirpan.helloreminder.ui.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padillahirpan.helloreminder.ui.data.ItemNote
import com.padillahirpan.helloreminder.ui.data.NoteType
import com.padillahirpan.helloreminder.ui.data.repository.LocalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */

class HomeViewModel(
    localRepository: LocalRepository
): ViewModel() {

    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     */
    val homeUiState: StateFlow<HomeUiState> =
        localRepository.getAllItemsStream().map { list ->
            val dataMapping = list.map { note ->
                ItemNote(
                    id = note.id,
                    name = note.name,
                    description = note.description,
                    type = when(note.type) {
                        NoteType.IN_PLAN.id -> NoteType.IN_PLAN.description
                        NoteType.ON_GOING.id -> NoteType.ON_GOING.description
                        NoteType.COMPLETED.id -> NoteType.COMPLETED.description
                        else -> ""
                    }
                )
            }
            HomeUiState(dataMapping)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val itemList: List<ItemNote> = listOf())