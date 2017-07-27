package clwater.weatherbykotlin.domain.model

/**
 * Created by gengzhibo on 17/7/27.
 */
data class ForecastList(val city: String, val country: String,
                        val dailyForecast: List<clwater.weatherbykotlin.domain.model.Forecast>) {
    operator fun  get(position: Int): Forecast {
        return  dailyForecast[position]
    }
}