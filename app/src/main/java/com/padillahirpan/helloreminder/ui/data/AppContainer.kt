package com.padillahirpan.helloreminder.ui.data

import android.content.Context
import com.padillahirpan.helloreminder.ui.data.db.HelloReminderDatabase
import com.padillahirpan.helloreminder.ui.data.repository.LocalRepository
import com.padillahirpan.helloreminder.ui.data.repository.LocalRepositoryImpl

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */

interface AppContainer {
    val localRepository: LocalRepository
}

class AppDataContainer(
    private val context: Context
): AppContainer {
    override val localRepository: LocalRepository by lazy {
        LocalRepositoryImpl(HelloReminderDatabase.getDatabase(context).noteDao())
    }

}