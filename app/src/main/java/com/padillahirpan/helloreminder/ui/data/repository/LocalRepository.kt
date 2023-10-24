package com.padillahirpan.helloreminder.ui.data.repository

import com.padillahirpan.helloreminder.ui.data.ItemNote
import com.padillahirpan.helloreminder.ui.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */
interface LocalRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<NoteEntity>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<NoteEntity?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: NoteEntity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: NoteEntity)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: NoteEntity)
}