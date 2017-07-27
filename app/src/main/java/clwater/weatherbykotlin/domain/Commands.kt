package clwater.weatherbykotlin.domain

/**
 * Created by gengzhibo on 17/7/27.
 */

public interface Command<T> {
    fun execute(): T
}



//data class ForecastList(val city: String, val country: String,
//                        val dailyForecast:List<Forecast>)
//
//data class Forecast(val date: String, val description: String, val high: Int,
//                    val low: Int)
