package clwater.weatherbykotlin.Utils

import android.util.JsonReader
import android.util.Log
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
    fun analysisCityList(cityList : String){
        var cityList = cityList.replace("var city_data=" , "")
        cityList = cityList.replace(";" , "")
        cityList = cityList.replace("AREAID" , "\"AREAID\"")
        cityList = cityList.replace("NAMECN" , "\"NAMECN\"")

        var rand : Reader = StringReader(cityList)
        var jsonObject = JsonReader(rand)
        jsonObject.beginObject()

        while (jsonObject.hasNext()){
            var text = jsonObject.nextName()
            Log.d("gzb" , "text1 : " + text)

            jsonObject.beginObject()
            while (jsonObject.hasNext()){
                var text = jsonObject.nextName()
                Log.d("gzb" , "text2 : " + text)

                jsonObject.beginObject()

                while (jsonObject.hasNext()){
                    var text = jsonObject.nextName()
                    Log.d("gzb" , "text3 : " + text)


                    while (jsonObject.hasNext()){
                        var text = jsonObject.nextName()
                        Log.d("gzb" , "text4 : " + text)

                        if(text.equals("AREAID")){
                            Log.d("gzb" , "text41 : " + jsonObject.nextString())
                        }else if (text.equals("NAMECN")){
                            Log.d("gzb" , "text42 : " + jsonObject.nextString())
                        }else {
                            jsonObject.skipValue()
                        }

                    }

                }
            }
        }

//        var jsonArray = JSONArray(cityList)
//        Log.d("gzb" , jsonArray.get(0).toString())
    }
}