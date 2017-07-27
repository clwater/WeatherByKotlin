package clwater.weatherbykotlin.domain.model

/**
 * Created by gengzhibo on 17/7/27.
 */
data class Forecast(val date: String, val description: String,
                    val high: Int, val low: Int, val iconUrl: String)