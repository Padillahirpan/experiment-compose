package com.padillahirpan.helloreminder.ui.data.repository

import com.padillahirpan.helloreminder.ui.data.ItemNote
import com.padillahirpan.helloreminder.ui.data.db.NoteDao
import com.padillahirpan.helloreminder.ui.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */

class LocalRepositoryImpl(
    private val noteDao: NoteDao
): LocalRepository {
    override fun getAllItemsStream(): Flow<List<NoteEntity>> {
        return noteDao.getAllItems()
    }

    override fun getItemStream(id: Int): Flow<NoteEntity?> {
        return noteDao.getItem(id)
    }

    override suspend fun insertItem(item: NoteEntity) {
        noteDao.insert(item)
    }

    override suspend fun deleteItem(item: NoteEntity) {
        noteDao.delete(item)
    }

    override suspend fun updateItem(item: NoteEntity) {
        noteDao.update(item)
    }
}