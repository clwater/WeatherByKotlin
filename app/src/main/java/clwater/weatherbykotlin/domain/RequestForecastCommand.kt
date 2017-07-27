package clwater.weatherbykotlin.domain

import clwater.weatherbykotlin.ForecastRequest
import clwater.weatherbykotlin.domain.model.ForecastList

/**
 * Created by gengzhibo on 17/7/27.
 */

class RequestForecastCommand(private  val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
                forecastRequest.execute())
    }
}