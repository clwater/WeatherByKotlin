package clwater.weatherbykotlin.Utils

import android.util.Log
import java.net.URL

/**
 * Created by gengzhibo on 17/8/17.
 */



class Request(val url: String) {
    fun run() : String{
        val forecastJsonStr = URL(url).readText()
        return forecastJsonStr
    }
}