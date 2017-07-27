package clwater.weatherbykotlin

/**
 * Created by gengzhibo on 17/7/27.
 */

import clwater.weatherbykotlin.domain.model.Forecast
public interface OnItemClickListener {
    operator fun invoke(forecast: Forecast)
}