package com.padillahirpan.helloreminder.ui.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */

@Dao
interface NoteDao {
    @Query("SELECT * from note ORDER BY name ASC")
    fun getAllItems(): Flow<List<NoteEntity>>

    @Query("SELECT * from note WHERE id = :id")
    fun getItem(id: Int): Flow<NoteEntity>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: NoteEntity)

    @Update
    suspend fun update(item: NoteEntity)

    @Delete
    suspend fun delete(item: NoteEntity)
}