package clwater.weatherbykotlin.Utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by yszsyf on 2017/8/30.
 */
object Times{
    fun getHour() : Int{
        val time=System.currentTimeMillis()
        val cal = Calendar.getInstance()
        cal.timeInMillis = time
        return cal.get(Calendar.HOUR_OF_DAY)
    }

    fun getWeek() : String{
        val time = System.currentTimeMillis()
        val date = Date(time)
        val format = SimpleDateFormat("E");
        return format.format(date)
    }
}