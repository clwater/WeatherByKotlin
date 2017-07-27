package clwater.weatherbykotlin

import android.util.Log
import java.net.URL

/**
 * Created by gengzhibo on 17/7/27.
 */

public class Request(val url : String){
    public fun run(): String {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName , forecastJsonStr)
        return forecastJsonStr
    }


}