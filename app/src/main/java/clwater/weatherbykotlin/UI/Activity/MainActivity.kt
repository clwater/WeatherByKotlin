package clwater.weatherbykotlin.UI.Activity

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import clwater.weatherbykotlin.EventBus.EB_ResultCityChoose
import clwater.weatherbykotlin.Model.WeatherModel
import clwater.weatherbykotlin.Model.WeatherNowModel
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.Utils.Analysis
import clwater.weatherbykotlin.Utils.Preference
import clwater.weatherbykotlin.Utils.Request
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.*
import android.view.WindowManager
import android.R.attr.tag







class MainActivity : AppCompatActivity() {

    private var cityName: String by Preference(this, "cityName", "")
    private var cityId : String by Preference(this, "cityId", "")

    private lateinit var weatherList : ArrayList<WeatherModel>
    private lateinit var weathernow : WeatherNowModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)
        supportActionBar?.hide()

        checkChooseData()
        initView()

    }

    private fun initView() {
//        initRefresh()
        initChoose()
        initScroll()
//        initMainHeight()

    }

    private fun initMainHeight() {
        val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val ll = rela_nowWeather.layoutParams
        ll.height = wm.defaultDisplay.height / 4 * 3
        rela_nowWeather.layoutParams = ll

    }

    private fun initChoose() {
        textview_main_citylist.setOnClickListener {  startActivity<ChooseCityActivity>()}
    }

    private fun initScroll() {
        swipeRefreshLayout_main.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED)
        swipeRefreshLayout_main.setOnRefreshListener({updataWeatherData()})
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
        swipeRefreshLayout_main.isRefreshing = true
        doAsync {
            val geoList = getCityGeo()
            val url = String.format("http://m.weather.com.cn/d/town/index?lat=%s&lon=%s" , geoList.get(1) , geoList.get(0) )
            val cityInfoText = Request(url).run()
            Log.d("gzb" , "url: " +url)
            weatherList = Analysis.analysisCityInfo(cityInfoText)
            weathernow =  Analysis.analysisCityNow(cityInfoText)
            uiThread {
                updataView()
                swipeRefreshLayout_main.isRefreshing = false
            }
        }
    }

    private fun updataView() {
        updataNowView()
        updataAfterView()

    }

    private fun updataAfterView() {
        val after0 = weatherList.get(0)
        val after1 = weatherList.get(1)
        val after2 = weatherList.get(2)
        val after3 = weatherList.get(3)
        val after4 = weatherList.get(4)

        textview_d0_w0.text = index2text(after0.WEATHER1)
        textview_d0_w1.text = index2text(after0.WEATHER2)
        textview_d0_i0.background = getDrawable(index2image(after0.WEATHER1))
        textview_d0_i1.background = getDrawable(index2image(after0.WEATHER2))
        textview_d0_t0.text = after0.TEMMAX.toString()
        textview_d0_t1.text = after0.TEMMIN.toString()

        textview_d1_w0.text = index2text(after1.WEATHER1)
        textview_d1_w1.text = index2text(after1.WEATHER2)
        textview_d1_i0.background = getDrawable(index2image(after1.WEATHER1))
        textview_d1_i1.background = getDrawable(index2image(after1.WEATHER2))
        textview_d1_t0.text = after1.TEMMAX.toString()
        textview_d1_t1.text = after1.TEMMIN.toString()

        textview_d2_w0.text = index2text(after2.WEATHER1)
        textview_d2_w1.text = index2text(after2.WEATHER2)
        textview_d2_i0.background = getDrawable(index2image(after2.WEATHER1))
        textview_d2_i1.background = getDrawable(index2image(after2.WEATHER2))
        textview_d2_t0.text = after2.TEMMAX.toString()
        textview_d2_t1.text = after2.TEMMIN.toString()

        textview_d3_w0.text = index2text(after3.WEATHER1)
        textview_d3_w1.text = index2text(after3.WEATHER2)
        textview_d3_i0.background = getDrawable(index2image(after3.WEATHER1))
        textview_d3_i1.background = getDrawable(index2image(after3.WEATHER2))
        textview_d3_t0.text = after3.TEMMAX.toString()
        textview_d3_t1.text = after3.TEMMIN.toString()

        textview_d4_w0.text = index2text(after4.WEATHER1)
        textview_d4_w1.text = index2text(after4.WEATHER2)
        textview_d4_i0.background = getDrawable(index2image(after4.WEATHER1))
        textview_d4_i1.background = getDrawable(index2image(after4.WEATHER2))
        textview_d4_t0.text = after4.TEMMAX.toString()
        textview_d4_t1.text = after4.TEMMIN.toString()
    }




    private fun updataNowView() {
        textview_now_updata.setText(weathernow.updataTime)
        textview_now_temp.setText(weathernow.temp + "°")
        textview_now_weather.setText(weathernow.weather)
        textview_now_wind.setText(weathernow.wind)
        textview_now_wet.setText(weathernow.wet)
    }

    private fun getCityGeo() :List<String>{
        val url = String.format("http://mobile.weather.com.cn/data/forecast/%s.html", cityId)
        Log.d("gzb" , "url: " + url)
        val cityInfoText = Request(url).run()
        return Analysis.analysisCityGeo(cityInfoText)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EB_ResultCityChoose(e: EB_ResultCityChoose){
        cityName = e.name
        cityId = e.id

        textview_main_cityname.setText(cityName)
    }

    private fun index2image(index: String): Int {

        val field = R.drawable::class.java.getField("icon_weather_" + index)
        val i = field.getInt(R.drawable())
        return i

    }

    private fun index2text(index: String): String {
        when (index){
            "00"-> return "晴"
            "01"-> return "多云"
            "02"-> return "阴"
            "03"-> return "阵雨"
            "04"-> return "雷阵雨"
            "05"-> return "雷阵雨伴有冰雹"
            "06"-> return "雨夹雪"
            "07"-> return "小雨"
            "08"-> return "中雨"
            "09"-> return "大雨"
            "10"-> return "暴雨"
            "11"-> return "大暴雨"
            "12"-> return "特大暴雨"
            "13"-> return "阵雪"
            "14"-> return "小雪"
            "15"-> return "中雪"
            "16"-> return "大雪"
            "17"-> return "暴雪"
            "18"-> return "雾"
            "19"-> return "冻雨"
            "20"-> return "沙尘暴"
            "21"-> return "小到中雨"
            "22"-> return "中到大雨"
            "23"-> return "大到暴雨"
            "24"-> return "暴雨到大暴雨"
            "25"-> return "大暴雨到特大暴雨"
            "26"-> return "小到中雪"
            "27"-> return "中到大雪"
            "28"-> return "大到暴雪"
            "29"-> return "浮沉"
            "30"-> return "扬沙"
            "31"-> return "沙尘暴"
            "53"-> return "霾"
            else-> return "未知"
        }

    }
}

