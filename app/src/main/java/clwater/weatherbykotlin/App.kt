package clwater.weatherbykotlin

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by gengzhibo on 17/7/27.
 */

class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}

