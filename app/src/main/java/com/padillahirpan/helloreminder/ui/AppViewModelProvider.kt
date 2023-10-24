package com.padillahirpan.helloreminder.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.padillahirpan.helloreminder.App
import com.padillahirpan.helloreminder.ui.page.home.HomeViewModel
import com.padillahirpan.helloreminder.ui.page.note.CreateNoteViewModel
import com.padillahirpan.helloreminder.ui.page.note.DetailViewModel

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
//        initializer {
//            ItemEditViewModel(
//                this.createSavedStateHandle(),
//                inventoryApplication().container.itemsRepository
//            )
//        }
        // Initializer for ItemEntryViewModel
        initializer {
            CreateNoteViewModel(noteApplication().container.localRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                noteApplication().container.localRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(
                noteApplication().container.localRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.noteApplication(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)