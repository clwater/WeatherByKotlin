package clwater.weatherbykotlin.UI.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import clwater.weatherbykotlin.EventBus.EB_ResultCityChoose
import clwater.weatherbykotlin.Model.WeatherModel
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.UI.Layout.MainActivityUI
import clwater.weatherbykotlin.Utils.Analysis
import clwater.weatherbykotlin.Utils.Preference
import clwater.weatherbykotlin.Utils.Request
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async

class MainActivity : AppCompatActivity() {

    private var cityName: String by Preference(this, "cityName", "")
    private var cityId : String by Preference(this, "cityId", "")

    private lateinit var weatherList : ArrayList<WeatherModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        supportActionBar?.hide()
        init()
    }

    private fun init() {
//        initRefresh()
        initview()
        checkChooseData()

        rela_main_infotext.setOnClickListener { updataWeatherData() }
    }

    private fun checkChooseData(){
        Log.d("gzb" , "cityName: " + cityName)
        if (!cityName.equals("")){
            textview_main_cityname.text = cityName
            updataWeatherData()
        } else{
            startActivity<ChooseCityActivity>()
        }
    }

    private fun updataWeatherData() {

        doAsync {


            val geoList = getCityGeo()
            val url = String.format("http://m.weather.com.cn/d/town/index?lat=%s&lon=%s" , geoList.get(1) , geoList.get(0) )
//            val url = String("http://m.weather.com.cn/d/town/index?lat=39.904989&lon=116.405285")
            val cityInfoText = Request(url).run()
            Log.d("gzb" , "url: " +url)
//            Log.d("gzb" , "cityInfoText: " +cityInfoText)
            weatherList = Analysis.analysisCityInfo(cityInfoText)
            Analysis.analysisCityNow(cityInfoText)

            uiThread {
                updataView()
            }
        }
    }

    private fun updataView() {

    }

    private fun getCityGeo() :List<String>{
        val url = String.format("http://mobile.weather.com.cn/data/forecast/%s.html", cityId)
        Log.d("gzb" , "url: " + url)
        val cityInfoText = Request(url).run()
        return Analysis.analysisCityGeo(cityInfoText)
    }

    private fun initview() {
        textview_main_citylist.setOnClickListener {  startActivity<ChooseCityActivity>()}

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EB_ResultCityChoose(e: EB_ResultCityChoose){
        cityName = e.name
        cityId = e.id

        textview_main_cityname.setText(cityName)
    }


}

