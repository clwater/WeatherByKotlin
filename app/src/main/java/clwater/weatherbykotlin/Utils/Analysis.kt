package clwater.weatherbykotlin.Utils

import android.util.JsonReader
import android.util.Log
import clwater.weatherbykotlin.Model.Province
import clwater.weatherbykotlin.Model.WeatherModel
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.Reader
import java.io.StringReader
import java.util.logging.Logger
import java.util.regex.Pattern

/**
 * Created by gengzhibo on 17/8/17.
 */

object Analysis{
    fun analysisCityList(cityList: String) : ArrayList<Province>{
        var tempcitylist = cityList.replace("var city_data={" , "")
        tempcitylist = tempcitylist.replace("};" , " ")
        tempcitylist = tempcitylist.replace("AREAID" , "\"AREAID\"")
        tempcitylist = tempcitylist.replace("NAMECN" , "\"NAMECN\"")


        val citylist = tempcitylist.split("}}},")

        val provinceList = ArrayList<Province>()

        var strBffer   = StringBuffer()


        for (index in 0.rangeTo(citylist.size - 1)) {
            val province = Province()

            var tempProvince = citylist[index]
            tempProvince = " {" + tempProvince + "}}}}"

            val provinceStringReader = StringReader(tempProvince)
            val provinceJsonReader = JsonReader(provinceStringReader)
            provinceJsonReader.beginObject()
            province.Pname = provinceJsonReader.nextName()

            val _cityList = ArrayList<Province.City>()

            provinceJsonReader.beginObject()

            while (provinceJsonReader.hasNext()) {
                val city  = Province.City()
                city.Cname = provinceJsonReader.nextName()
                val _reginList  = ArrayList<Province.City.Region>()

                provinceJsonReader.beginObject()

                strBffer.setLength(0)

                while (provinceJsonReader.hasNext()) {
                    val region  = Province.City.Region()
                    region.Rname = provinceJsonReader.nextName()

                    provinceJsonReader.beginObject()
                    provinceJsonReader.nextName()

                    region.Id = provinceJsonReader.nextString()

                    provinceJsonReader.nextName()
                    provinceJsonReader.nextString()

                    provinceJsonReader.endObject()

                    _reginList.add(region)
                }
                provinceJsonReader.endObject()

                city.RegionList = _reginList
                _cityList.add(city)
            }
            provinceJsonReader.endObject()
            provinceJsonReader.endObject()

            province.CityList = _cityList
            provinceList.add(province)

        }

        return provinceList
    }
    fun analysisCityInfo(cityInfo: String) : ArrayList<WeatherModel>{

        val pattern = """\{\"TEMMIN.*?\}"""

        val mat = Regex(pattern).findAll(cityInfo).toList()
        val weatherList = ArrayList<WeatherModel>()

        for (value : MatchResult in mat){
            Log.d("gzb" , value.value )
            val weatherModel : WeatherModel? = null
            val jsonObject =  JSONObject(value.value)
            weatherModel?.reftime = jsonObject.getString("reftime")
            weatherModel?.TEMMIN = jsonObject.getInt("TEMMIN")
            weatherModel?.TEMMAX = jsonObject.getInt("TEMMAX")
            weatherModel?.WIND1 = jsonObject.getString("WIND1")
            weatherModel?.WINS1 = jsonObject.getString("WINS1")
            weatherModel?.WIND2 = jsonObject.getString("WIND2")
            weatherModel?.WINS2 = jsonObject.getString("WINS2")
            weatherModel?.WEATHER1 = jsonObject.getString("WEATHER1")
            weatherModel?.WEATHER2 = jsonObject.getString("WEATHER2")
            weatherList.add(weatherModel!!)
        }

        return weatherList
    }

    fun analysisCityGeo(cityGeo: String) : List<String>{
        val _list = ArrayList<String>()

        val jsonObject = JSONObject(cityGeo)
        val jsonObject_C = jsonObject.getJSONObject("c")
        _list.add(jsonObject_C.getString("c13"))
        _list.add(jsonObject_C.getString("c14"))

        return _list
    }
}