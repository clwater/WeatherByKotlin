package clwater.weatherbykotlin.domain


import clwater.weatherbykotlin.Forecast
import clwater.weatherbykotlin.ForecastResult
import clwater.weatherbykotlin.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import clwater.weatherbykotlin.domain.model.Forecast as ModelForecast

/**
 * Created by gengzhibo on 17/7/27.
 */
public class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }
    private fun convertForecastListToDomain(list: List<Forecast>):
            List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }
    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt() , generateInconUrl(forecast.weather[0].icon))
    }
    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateInconUrl(iconCode : String) : String = "http://openweathermap.org/img/w/$iconCode.png"
}