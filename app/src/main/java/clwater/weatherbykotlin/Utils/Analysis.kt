package clwater.weatherbykotlin.Utils

import android.util.JsonReader
import android.util.Log
import clwater.weatherbykotlin.Model.Province
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.Reader
import java.io.StringReader
import java.util.logging.Logger

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
    fun analysisCityInfo(cityInfo: String) {
       var  regexMatch = "TEMMIN".toRegex()

        val info = regexMatch.matches(cityInfo)
        Log.d("gzb" , info.toString())
    }
}