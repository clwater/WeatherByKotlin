package clwater.weatherbykotlin

import android.util.Log
import com.google.gson.Gson

public class ForecastRequest(val  zipCode : String){
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute() : ForecastResult{
        val forecastJsonStr = java.net.URL("http://m.weather.com.cn/d/town/index?lat=38.91222&lon=121.602219").readText()
        Log.d("gzb" , forecastJsonStr)
        return Gson().fromJson(forecastJsonStr , ForecastResult::class.java)
    }
}