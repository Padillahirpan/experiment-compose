package com.padillahirpan.helloreminder

import android.app.Application
import com.padillahirpan.helloreminder.ui.data.AppContainer
import com.padillahirpan.helloreminder.ui.data.AppDataContainer

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */
class App: Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}